package com.github.pedroluis02.springsecuritysample.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticateFilter(
    private val tokenProvider: JwtTokenProvider,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    private companion object {
        const val BEARER_FILTER = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (authHeader.containsBearerToken()) {
            val token = authHeader.extractBearerToken()
            val username = tokenProvider.extractUsername(token)
            val user = userDetailsService.loadUserByUsername(username)

            if (tokenProvider.isValid(token, user)) {
                setUserAuthToContext(request, user)
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun String?.containsBearerToken() = this != null && this.startsWith(BEARER_FILTER)

    private fun String?.extractBearerToken() = this!!.substringAfter(BEARER_FILTER)

    private fun setUserAuthToContext(request: HttpServletRequest, user: UserDetails) {
        val authToken = UsernamePasswordAuthenticationToken(user, null, user.authorities)
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authToken
    }
}
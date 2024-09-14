package com.github.pedroluis02.springsecuritysample.service

import com.github.pedroluis02.springsecuritysample.domain.service.AuthService
import com.github.pedroluis02.springsecuritysample.security.JwtTokenProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val authManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val tokenProvider: JwtTokenProvider
) : AuthService {

    override fun authenticate(username: String, password: String): String {
        authManager.authenticate(UsernamePasswordAuthenticationToken(username, password))

        val user = userDetailsService.loadUserByUsername(username)
        return tokenProvider.generate(user)
    }
}
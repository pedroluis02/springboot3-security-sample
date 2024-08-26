package com.github.pedroluis02.springsecuritysample.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(private val jwtSecurityProps: JwtSecurityProps) {

    fun generate(user: UserDetails): String =
        Jwts.builder()
            .claims()
            .subject(user.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + jwtSecurityProps.expiration))
            .and()
            .signWith(secretKey)
            .compact()

    fun isValid(token: String, user: UserDetails): Boolean {
        val claims = parseClaims(token)
        return (claims.subject == user.username)
                && (claims.expiration.after(Date(System.currentTimeMillis())))
    }

    fun extractUsername(token: String): String =
        parseClaims(token).subject ?: throw UsernameNotFoundException("Username not found in token")

    private val secretKey = Keys.hmacShaKeyFor(jwtSecurityProps.secret.toByteArray())

    private fun parseClaims(token: String) =
        Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
}
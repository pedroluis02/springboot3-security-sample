package com.github.pedroluis02.springsecuritysample.security

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app.security.jwt")
data class JwtSecurityProps(
    val secret: String,
    val expiration: Long
)

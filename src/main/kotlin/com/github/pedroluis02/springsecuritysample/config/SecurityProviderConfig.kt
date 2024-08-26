package com.github.pedroluis02.springsecuritysample.config

import com.github.pedroluis02.springsecuritysample.security.JwtSecurityProps
import com.github.pedroluis02.springsecuritysample.security.UserDetailsServiceImpl
import com.github.pedroluis02.springsecuritysample.service.UserService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableConfigurationProperties(JwtSecurityProps::class)
class SecurityProviderConfig {

    @Bean
    fun userDetailsService(userService: UserService): UserDetailsService =
        UserDetailsServiceImpl(userService)

    @Bean
    fun authenticationProvider(userService: UserService): AuthenticationProvider =
        DaoAuthenticationProvider().apply {
            setUserDetailsService(userDetailsService(userService))
            setPasswordEncoder(passwordEncoder())
        }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
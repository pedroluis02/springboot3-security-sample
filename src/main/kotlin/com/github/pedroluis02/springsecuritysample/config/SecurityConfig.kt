package com.github.pedroluis02.springsecuritysample.config

import com.github.pedroluis02.springsecuritysample.security.JwtAuthenticateFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authenticationProvider: AuthenticationProvider,
    private val jwtAuthenticateFilter: JwtAuthenticateFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? =
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/users").permitAll()
                    .anyRequest().fullyAuthenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticateFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()

}
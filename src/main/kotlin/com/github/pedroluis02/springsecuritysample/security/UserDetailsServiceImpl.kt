package com.github.pedroluis02.springsecuritysample.security

import com.github.pedroluis02.springsecuritysample.domain.model.User
import com.github.pedroluis02.springsecuritysample.domain.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as UserDetailsImpl

@Service
class UserDetailsServiceImpl(private val service: UserService) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return service.findByUsername(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("Not found '$username'")
    }

    private fun User.mapToUserDetails() =
        UserDetailsImpl.builder()
            .username(this.username)
            .password(this.password)
            .build()
}
package com.github.pedroluis02.springsecuritysample.service

import com.github.pedroluis02.springsecuritysample.domain.model.User
import com.github.pedroluis02.springsecuritysample.domain.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val passwordEncoder: PasswordEncoder) : UserService {

    private val users = mutableListOf(
        User(
            id = 1L,
            name = "Admin",
            username = "admin",
            password = passwordEncoder.encode("admin")
        )
    )

    override fun create(name: String, username: String, password: String): User? =
        findByUsername(username) ?: createNew(name, username, password)

    override fun findByUsername(username: String): User? =
        users.firstOrNull { it.username == username }

    private fun createNew(name: String, username: String, password: String): User {
        val newId = users.size + 1
        val newPassword = passwordEncoder.encode(password)
        val newUser = User(newId.toLong(), name, username, newPassword)
        users.addLast(newUser)

        return newUser
    }
}
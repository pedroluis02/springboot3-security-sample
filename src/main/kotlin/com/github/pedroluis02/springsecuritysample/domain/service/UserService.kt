package com.github.pedroluis02.springsecuritysample.domain.service

import com.github.pedroluis02.springsecuritysample.domain.model.User

interface UserService {
    fun create(name: String, username: String, password: String): User?
    fun findByUsername(username: String): User?
}
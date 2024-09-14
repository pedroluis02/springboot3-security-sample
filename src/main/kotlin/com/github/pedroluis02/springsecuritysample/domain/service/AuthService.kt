package com.github.pedroluis02.springsecuritysample.domain.service

fun interface AuthService {
    fun authenticate(username: String, password: String): String
}
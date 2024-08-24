package com.github.pedroluis02.springsecuritysample.domain.model

data class User(
    val id: Long,
    val name: String,
    val username: String,
    val password: String
)

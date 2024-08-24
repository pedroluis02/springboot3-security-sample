package com.github.pedroluis02.springsecuritysample.apirestful.dto

data class UserRequestDto(
    val name: String,
    val username: String,
    val password: String
)
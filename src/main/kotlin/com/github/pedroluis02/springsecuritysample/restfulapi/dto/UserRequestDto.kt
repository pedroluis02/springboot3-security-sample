package com.github.pedroluis02.springsecuritysample.restfulapi.dto

data class UserRequestDto(
    val name: String,
    val username: String,
    val password: String
)
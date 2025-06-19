package com.github.pedroluis02.springsecuritysample.restfulapi.dto

data class AccountDto(
    val id: String,
    val name: String,
    val contacts: List<AccountDto> = emptyList()
)
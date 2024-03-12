package com.github.pedroluis02.springsecuritysample.apirestful.dto

data class AccountDto(
    val id: String,
    val name: String,
    val contacts: List<AccountDto> = emptyList()
)
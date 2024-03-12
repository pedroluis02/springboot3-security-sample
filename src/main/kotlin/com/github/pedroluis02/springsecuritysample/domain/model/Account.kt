package com.github.pedroluis02.springsecuritysample.domain.model

data class Account(
    val id: String,
    val name: String,
    val contacts: List<Account> = emptyList()
)
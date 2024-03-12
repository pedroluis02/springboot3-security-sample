package com.github.pedroluis02.springsecuritysample.apirestful.mapper

import com.github.pedroluis02.springsecuritysample.apirestful.dto.AccountDto
import com.github.pedroluis02.springsecuritysample.domain.model.Account

fun Account.toDto(): AccountDto {
    return AccountDto(id, name)
}
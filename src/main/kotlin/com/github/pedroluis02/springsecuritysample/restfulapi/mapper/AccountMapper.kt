package com.github.pedroluis02.springsecuritysample.restfulapi.mapper

import com.github.pedroluis02.springsecuritysample.domain.model.Account
import com.github.pedroluis02.springsecuritysample.restfulapi.dto.AccountDto

fun Account.toDto(): AccountDto {
    return AccountDto(id, name)
}
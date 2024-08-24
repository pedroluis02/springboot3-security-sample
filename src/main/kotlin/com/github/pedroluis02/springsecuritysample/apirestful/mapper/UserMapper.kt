package com.github.pedroluis02.springsecuritysample.apirestful.mapper

import com.github.pedroluis02.springsecuritysample.apirestful.dto.UserResultDto
import com.github.pedroluis02.springsecuritysample.domain.model.User

fun User.toDto() = UserResultDto(
    id = id,
    name = name,
    username = username
)
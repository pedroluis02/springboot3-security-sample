package com.github.pedroluis02.springsecuritysample.restfulapi.mapper

import com.github.pedroluis02.springsecuritysample.domain.model.User
import com.github.pedroluis02.springsecuritysample.restfulapi.dto.UserResultDto

fun User.toDto() = UserResultDto(
    id = id,
    name = name,
    username = username
)
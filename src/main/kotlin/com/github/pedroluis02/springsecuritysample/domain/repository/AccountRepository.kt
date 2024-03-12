package com.github.pedroluis02.springsecuritysample.domain.repository;

import com.github.pedroluis02.springsecuritysample.domain.model.Account

fun interface AccountRepository {
    fun findAllById(id: String): List<Account>
}

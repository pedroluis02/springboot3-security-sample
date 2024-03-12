package com.github.pedroluis02.springsecuritysample.datasource

import com.github.pedroluis02.springsecuritysample.domain.model.Account
import com.github.pedroluis02.springsecuritysample.domain.repository.AccountRepository
import org.springframework.stereotype.Component

@Component
class AccountRepositoryImpl : AccountRepository {

    override fun findAllById(id: String): List<Account> {
        return listOf(Account("1", "Account-1"), Account("2", "Account-2"))
    }
}
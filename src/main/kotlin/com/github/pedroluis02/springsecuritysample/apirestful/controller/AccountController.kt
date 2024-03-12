package com.github.pedroluis02.springsecuritysample.apirestful.controller

import com.github.pedroluis02.springsecuritysample.apirestful.dto.AccountDto
import com.github.pedroluis02.springsecuritysample.apirestful.mapper.mapToList
import com.github.pedroluis02.springsecuritysample.apirestful.mapper.toDto
import com.github.pedroluis02.springsecuritysample.domain.model.Account
import com.github.pedroluis02.springsecuritysample.domain.repository.AccountRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController(private val repository: AccountRepository) {

    @GetMapping("/{id}/contacts")
    fun findContactsBy(@PathVariable id: String):
            ResponseEntity<List<AccountDto>> {
        val contacts = repository.findAllById(id);
        val dto = contacts.mapToList(Account::toDto)
        return ResponseEntity.ok(dto)
    }
}
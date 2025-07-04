package com.github.pedroluis02.springsecuritysample.restfulapi.controller

import com.github.pedroluis02.springsecuritysample.domain.service.UserService
import com.github.pedroluis02.springsecuritysample.restfulapi.dto.UserRequestDto
import com.github.pedroluis02.springsecuritysample.restfulapi.mapper.toDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/users")
class UserController(private val service: UserService) {

    @PostMapping
    fun create(@RequestBody body: UserRequestDto): ResponseEntity<*> {
        val user = service.create(body.name, body.username, body.password)
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create user.")

        return ResponseEntity.status(HttpStatus.CREATED).body(user.toDto())
    }
}
package com.github.pedroluis02.springsecuritysample.apirestful.controller

import com.github.pedroluis02.springsecuritysample.apirestful.dto.LoginRequestDto
import com.github.pedroluis02.springsecuritysample.apirestful.dto.LoginResultDto
import com.github.pedroluis02.springsecuritysample.domain.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val service: AuthService) {

    @PostMapping
    fun authenticate(@RequestBody body: LoginRequestDto): ResponseEntity<*> {
        val token = service.authenticate(body.username, body.password)
        return ResponseEntity.ok().body(LoginResultDto(token))
    }
}
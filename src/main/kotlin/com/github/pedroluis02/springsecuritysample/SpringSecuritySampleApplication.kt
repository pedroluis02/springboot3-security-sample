package com.github.pedroluis02.springsecuritysample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringSecuritySampleApplication

fun main(args: Array<String>) {
	runApplication<SpringSecuritySampleApplication>(*args)
}

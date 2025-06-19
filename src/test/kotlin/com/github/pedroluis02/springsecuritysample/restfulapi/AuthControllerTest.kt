package com.github.pedroluis02.springsecuritysample.restfulapi

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.pedroluis02.springsecuritysample.config.SecurityConfigTest
import com.github.pedroluis02.springsecuritysample.domain.service.AuthService
import com.github.pedroluis02.springsecuritysample.restfulapi.controller.AuthController
import com.github.pedroluis02.springsecuritysample.restfulapi.dto.LoginRequestDto
import com.github.pedroluis02.springsecuritysample.security.JwtAuthenticateFilter
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(
    controllers = [AuthController::class],
    excludeFilters = [ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = [JwtAuthenticateFilter::class]
    )]
)
@Import(SecurityConfigTest::class)
class AuthControllerTest {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var service: AuthService

    @Test
    fun `Given username and password When authenticate Then retrieve token`() {
        val token = "access-token"

        val usernameCaptor = argumentCaptor<String>()
        whenever(service.authenticate(usernameCaptor.capture(), any())).thenReturn(token)

        val body = LoginRequestDto("admin", "admin")
        mockMvc.perform(
            MockMvcRequestBuilders.post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body))
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.token").value(token))

        Assertions.assertThat(usernameCaptor.firstValue).isEqualTo(body.username)
    }
}
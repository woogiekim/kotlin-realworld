package com.woogie.realworld.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.woogie.realworld.controller.dto.LoginData
import com.woogie.realworld.controller.dto.LoginRequest
import com.woogie.realworld.controller.dto.UserRegistrationData
import com.woogie.realworld.controller.dto.UserRegistrationRequest
import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.BaseApiTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class AuthApiTest @Autowired constructor(
    private val mockMvc: MockMvc
) : BaseApiTest() {

    @Test
    fun `사용자 등록`() {
        val req =
            UserRegistrationRequest(UserRegistrationData("김태욱", "wook@gmail.com", "1234567"))

        mockMvc.perform(
            post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(req))
        ).andExpectAll(
            status().isOk,
            jsonPath("$.user.username").value("김태욱"),
            jsonPath("$.user.email").value("wook@gmail.com"),
            jsonPath("$.user.token").isEmpty,
            jsonPath("$.user.bio").isEmpty,
            jsonPath("$.user.image").isEmpty
        )
    }

    @Test
    fun `로그인`() {
        val user = userRegistrationUseCase.register(createUser())

        val req =
            LoginRequest(LoginData(user.email.toString(), user.password.toString()))

        mockMvc.perform(
            post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(req))
        ).andExpectAll(
            status().isOk,
            jsonPath("$.user.username").value(user.username.toString()),
            jsonPath("$.user.email").value(user.email.toString()),
            jsonPath("$.user.token").isNotEmpty,
            jsonPath("$.user.bio").value(user.bio.toString()),
            jsonPath("$.user.image").value(user.image.toString())
        )
    }
}

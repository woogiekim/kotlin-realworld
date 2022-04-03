package com.woogie.realworld.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.woogie.realworld.controller.dto.UserRegisterRequest
import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.fixture.createUserEmail
import com.woogie.realworld.fixture.createUserPassword
import com.woogie.realworld.fixture.createUsername
import com.woogie.realworld.support.BaseApiTest
import com.woogie.realworld.user.service.UserRegistrationUseCase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class UserApiTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val userRegistrationUseCase: UserRegistrationUseCase
) : BaseApiTest() {

    @Test
    fun `사용자 등록`() {
        val req = UserRegisterRequest(createUsername(), createUserEmail(), createUserPassword())

        mockMvc.perform(
            post("/users")
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
    fun `현재 사용자 조회`() {
        val user = userRegistrationUseCase.register(createUser())

        mockMvc.perform(
            get("/users/{id}", user.id!!)
        ).andExpectAll(
            status().isOk,
            jsonPath("$.user.username").value("김태욱"),
            jsonPath("$.user.email").value("wook@gmail.com"),
            jsonPath("$.user.token").isEmpty,
            jsonPath("$.user.bio").isEmpty,
            jsonPath("$.user.image").isEmpty
        )
    }
}

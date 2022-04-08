package com.woogie.realworld.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.woogie.realworld.controller.dto.UserRegistrationData
import com.woogie.realworld.controller.dto.UserRegistrationRequest
import com.woogie.realworld.controller.dto.UserUpdateData
import com.woogie.realworld.controller.dto.UserUpdateRequest
import com.woogie.realworld.domain.user.service.UserRegistrationUseCase
import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.BaseApiTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class UserApiTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val userRegistrationUseCase: UserRegistrationUseCase
) : BaseApiTest() {

    @Test
    fun `사용자 등록`() {
        val req =
            UserRegistrationRequest(UserRegistrationData("김태욱", "wook@gmail.com", "1234567"))

        mockMvc.perform(
            post("/api/user")
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
            get("/api/user/{id}", user.id!!)
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
    fun `사용자 수정`() {
        val user = userRegistrationUseCase.register(createUser())

        val req = UserUpdateRequest(
            UserUpdateData(
                "Taewook Kim", "mdir2@naver.com", "7654321",
                "change.png", "복행"
            )
        )

        mockMvc.perform(
            put("/api/user/{id}", user.id!!)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(req))
        ).andExpectAll(
            status().isOk,
            jsonPath("$.user.username").value("Taewook Kim"),
            jsonPath("$.user.email").value("mdir2@naver.com"),
            jsonPath("$.user.token").isEmpty,
            jsonPath("$.user.bio").value("복행"),
            jsonPath("$.user.image").value("change.png")
        )
    }
}

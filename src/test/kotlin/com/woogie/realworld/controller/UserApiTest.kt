package com.woogie.realworld.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.woogie.realworld.controller.dto.UserUpdateData
import com.woogie.realworld.controller.dto.UserUpdateRequest
import com.woogie.realworld.support.BaseApiTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class UserApiTest @Autowired constructor(
    private val mockMvc: MockMvc
) : BaseApiTest() {

    @Test
    fun `현재 사용자 조회`() {
        val (user, token) = prepareLogInUser()

        mockMvc.perform(
            get("/api/user", user.id!!)
                .header(HttpHeaders.AUTHORIZATION, token)
        ).andExpectAll(
            status().isOk,
            jsonPath("$.user.username").value(user.username.toString()),
            jsonPath("$.user.email").value(user.email.toString()),
            jsonPath("$.user.token").value(token),
            jsonPath("$.user.bio").value(user.bio.toString()),
            jsonPath("$.user.image").value(user.image.toString())
        )
    }

    @Test
    fun `사용자 수정`() {
        val (user, token) = prepareLogInUser()

        val req = UserUpdateRequest(
            UserUpdateData(
                "Taewook Kim", "mdir2@naver.com", "7654321",
                "change.png", "복행"
            )
        )

        mockMvc.perform(
            put("/api/user", user.id!!)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
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

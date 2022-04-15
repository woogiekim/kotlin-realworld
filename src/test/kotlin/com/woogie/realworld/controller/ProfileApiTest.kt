package com.woogie.realworld.controller

import com.woogie.realworld.support.BaseApiTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class ProfileApiTest @Autowired constructor(
    private val mockMvc: MockMvc,
) : BaseApiTest() {

    @Test
    fun `프로필 조회`() {
        val (user, token) = prepareLogInUser()

        mockMvc.perform(
            get("/api/profile/{username}", user.username, user.id!!)
                .header(HttpHeaders.AUTHORIZATION, token)
        ).andExpectAll(
            status().isOk,
            jsonPath("$.profile.username").value(user.username.toString()),
            jsonPath("$.profile.bio").value(user.bio.toString()),
            jsonPath("$.profile.image").value(user.image.toString()),
            jsonPath("$.profile.following").value(false)
        )
    }
}

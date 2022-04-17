package com.woogie.realworld.controller

import com.woogie.realworld.domain.user.domain.UserEmail
import com.woogie.realworld.domain.user.domain.Username
import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.BaseApiTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class ProfileApiTest @Autowired constructor(
    private val mockMvc: MockMvc
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

    @Test
    fun `팔로우`() {
        val (_, token) = prepareLogInUser()

        val followee =
            userRegistrationUseCase.register(createUser(Username("followee"), UserEmail("followee@naver.com")))

        mockMvc.perform(
            post("/api/profile/{username}/follow", followee.username.toString())
                .header(HttpHeaders.AUTHORIZATION, token)
        ).andExpectAll(
            status().isOk,
            jsonPath("$.profile.username").value(followee.username.toString()),
            jsonPath("$.profile.bio").value(followee.bio.toString()),
            jsonPath("$.profile.image").value(followee.image.toString()),
            jsonPath("$.profile.following").value(true)
        )

        mockMvc.perform(
            post("/api/profile/{username}/follow", followee.username.toString())
                .header(HttpHeaders.AUTHORIZATION, token)
        ).andExpectAll(
            status().isOk,
            jsonPath("$.profile.username").value(followee.username.toString()),
            jsonPath("$.profile.bio").value(followee.bio.toString()),
            jsonPath("$.profile.image").value(followee.image.toString()),
            jsonPath("$.profile.following").value(false)
        )
    }
}

package com.woogie.realworld.controller

import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.BaseApiTest
import com.woogie.realworld.domain.user.service.UserRegistrationUseCase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

internal class ProfileApiTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val userRegistrationUseCase: UserRegistrationUseCase,
) : BaseApiTest() {

    @Test
    fun `프로필 조회`() {
        val user = userRegistrationUseCase.register(createUser())

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/profile/{id}", user.id!!)
        ).andExpectAll(
            MockMvcResultMatchers.status().isOk,
            MockMvcResultMatchers.jsonPath("$.profile.username").value("김태욱"),
            MockMvcResultMatchers.jsonPath("$.profile.bio").isEmpty,
            MockMvcResultMatchers.jsonPath("$.profile.image").isEmpty,
            MockMvcResultMatchers.jsonPath("$.profile.following").value(false)
        )
    }
}

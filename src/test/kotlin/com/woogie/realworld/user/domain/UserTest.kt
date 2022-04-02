package com.woogie.realworld.user.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserTest {
    @Test
    fun `사용자 생성 성공`() {
        val username = Username("김태욱")
        val email = UserEmail("wook@gmail.com")
        val password = UserPassword("1234567")

        val user = User(username, email, password)

        assertThat(user.name).isEqualTo(username)
        assertThat(user.email).isEqualTo(email)
        assertThat(user.password).isEqualTo(password)
        assertThat(user.createAt).isNotNull
    }
}

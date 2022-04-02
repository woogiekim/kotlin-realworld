package com.woogie.realworld.domain.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserTest {
    @Test
    fun `사용자 생성 성공`() {
        val email = UserEmail("wook@gmail.com")
        val username = Username("김태욱")
        val password = UserPassword("1234567")

        val user = User(email, username, password)

        assertThat(user.email).isEqualTo(email)
        assertThat(user.name).isEqualTo(username)
        assertThat(user.password).isEqualTo(password)
        assertThat(user.createAt).isNotNull
    }
}

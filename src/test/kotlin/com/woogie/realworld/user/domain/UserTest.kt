package com.woogie.realworld.user.domain

import com.woogie.realworld.fixture.createUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserTest {
    @Test
    fun `User 생성 성공`() {
        val username = Username("김태욱")
        val email = UserEmail("wook@gmail.com")
        val password = UserPassword("1234567")

        val user = User(username, email, password)

        assertThat(user.name).isEqualTo(username)
        assertThat(user.email).isEqualTo(email)
        assertThat(user.password).isEqualTo(password)
        assertThat(user.createAt).isNotNull
    }

    @Test
    fun `User 수정 성공`() {
        val user = createUser()

        assertThat(user.name).isEqualTo(Username("김태욱"))
        assertThat(user.email).isEqualTo(UserEmail("wook@gmail.com"))
        assertThat(user.password).isEqualTo(UserPassword("1234567"))

        user.update(
            Username("Taewook Kim"), UserEmail("mdir2@naver.com"), UserPassword("7654321"),
            UserImage("image.png"), UserBio("행복")
        )

        assertThat(user.name).isEqualTo(Username("Taewook Kim"))
        assertThat(user.email).isEqualTo(UserEmail("mdir2@naver.com"))
        assertThat(user.password).isEqualTo(UserPassword("7654321"))
        assertThat(user.image).isEqualTo(UserImage("image.png"))
        assertThat(user.bio).isEqualTo(UserBio("행복"))
    }
}

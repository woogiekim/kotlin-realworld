package com.woogie.realworld.domain.user.domain

import com.woogie.realworld.fixture.createUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserTest {
    @Test
    fun `User 생성 성공`() {
        val email = UserEmail("wook@gmail.com")
        val password = UserPassword("1234567")
        val username = Username("김태욱")

        val user = User.create(email, password, username)

        assertThat(user.username).isEqualTo(username)
        assertThat(user.email).isEqualTo(email)
        assertThat(user.password).isEqualTo(password)
        assertThat(user.bio).isNull()
        assertThat(user.image).isNull()
        assertThat(user.createAt).isNotNull
    }

    @Test
    fun `User 수정 성공`() {
        val user = createUser()

        val username = Username("김태욱")

        assertThat(user.username).isEqualTo(username)
        assertThat(user.email).isEqualTo(UserEmail("wook@gmail.com"))
        assertThat(user.password).isEqualTo(UserPassword("1234567"))

        val updatedUsername = Username("Taewook Kim")
        val updatedImage = UserImage("image.png")
        val updatedBio = UserBio("행복")

        user.update(
            UserEmail("mdir2@naver.com"), UserPassword("7654321"), updatedUsername,
            updatedBio, updatedImage
        )

        assertThat(user.username).isEqualTo(updatedUsername)
        assertThat(user.email).isEqualTo(UserEmail("mdir2@naver.com"))
        assertThat(user.password).isEqualTo(UserPassword("7654321"))
        assertThat(user.bio).isEqualTo(updatedBio)
        assertThat(user.image).isEqualTo(updatedImage)
    }
}

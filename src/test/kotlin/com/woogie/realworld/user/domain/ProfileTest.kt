package com.woogie.realworld.user.domain

import com.woogie.realworld.fixture.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ProfileTest {
    @Test
    fun `Profile 생성 성공`() {
        val profile = Profile(createUsername(), createUserBio(), createUserImage())

        assertThat(profile.username).isEqualTo(createUsername())
        assertThat(profile.bio).isEqualTo(createUserBio())
        assertThat(profile.image).isEqualTo(createUserImage())
    }

    @Test
    fun `Profile 수정 성공`() {
        val profile = createProfile()

        val updatedUsername = Username("Taewook Kim")
        val updatedBio = UserBio("복행")
        val updatedImage = UserImage("change.png")

        profile.update(updatedUsername, updatedBio, updatedImage)

        assertThat(profile.username).isEqualTo(updatedUsername)
        assertThat(profile.bio).isEqualTo(updatedBio)
        assertThat(profile.image).isEqualTo(updatedImage)
    }
}

package com.woogie.realworld.domain.user.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserBioTest {
    @Test
    fun `UserBio 생성 성공`() {
        val bio = UserBio("행복")

        assertThat(bio).isEqualTo(UserBio("행복"))
    }
}

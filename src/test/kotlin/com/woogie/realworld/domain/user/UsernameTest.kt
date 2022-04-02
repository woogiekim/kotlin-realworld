package com.woogie.realworld.domain.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UsernameTest {
    @Test
    fun `사용자 이름 생성 성공`() {
        val username = Username("김태욱")

        assertThat(username).isEqualTo(Username("김태욱"))
    }
}

package com.woogie.realworld.user.domain

import com.woogie.realworld.exception.RealWorldException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

internal class UserPasswordTest {
    @Test
    fun `사용자 비밀번호 생성 성공`() {
        val password = UserPassword("1234567")

        assertThat(password).isEqualTo(UserPassword("1234567"))
    }

    @Test
    fun `비밀번호 글자수가 7자리 보다 작으면 생성 실패`() {
        assertThatExceptionOfType(RealWorldException::class.java)
            .isThrownBy { UserPassword("123456") }
    }
}

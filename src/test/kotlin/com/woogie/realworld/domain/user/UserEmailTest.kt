package com.woogie.realworld.domain.user

import com.woogie.realworld.exception.RealWorldException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

internal class UserEmailTest {
    @Test
    fun `사용자 이메일 생성 성공`() {
        val email = UserEmail("wook@gmail.com")

        assertThat(email).isEqualTo(UserEmail("wook@gmail.com"))
    }

    @Test
    fun `@가 없으면 생성 실패`() {
        assertThatExceptionOfType(RealWorldException::class.java)
            .isThrownBy { UserEmail("wookgmail.com") }
    }

    @Test
    fun `dot이 없으면 생성 실패`() {
        assertThatExceptionOfType(RealWorldException::class.java)
            .isThrownBy { UserEmail("wook@gmail") }
    }

    @Test
    fun `dot이후 글자가 두 자리 이상이 아니면 생성 실패`() {
        assertThatExceptionOfType(RealWorldException::class.java)
            .isThrownBy { UserEmail("wook@gmail.c") }
    }
}

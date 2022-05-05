package com.woogie.realworld.domain.user.domain

import com.woogie.realworld.exception.ErrorCode.*
import com.woogie.realworld.support.assertThatRealWorldException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserEmailTest {
    @Test
    fun `UserEmail 생성 성공`() {
        val email = UserEmail("wook@gmail.com")

        assertThat(email).isEqualTo(UserEmail("wook@gmail.com"))
    }

    @Test
    fun `공백이 있으면 생성 실패`() {
        assertThatRealWorldException(REQUIRED) {
            UserEmail(" ")
        }
    }

    @Test
    fun `최대길이 보다 크면 생성 실패`() {
        val email = "wook@gmail.com"
        val tooLongEmail = (email.length until UserEmail.MAXIMUM_LENGTH + 1).joinToString("", email)

        assertThatRealWorldException(INVALID_EMAIL_MAXIMUM_LENGTH) {
            UserEmail(tooLongEmail)
        }
    }

    @Test
    fun `@가 없으면 생성 실패`() {
        assertThatRealWorldException(INVALID_EMAIL) {
            UserEmail("wookgmail.com")
        }
    }

    @Test
    fun `dot이 없으면 생성 실패`() {
        assertThatRealWorldException(INVALID_EMAIL) {
            UserEmail("wook@gmail")
        }
    }

    @Test
    fun `dot이후 글자가 두 자리 이상이 아니면 생성 실패`() {
        assertThatRealWorldException(INVALID_EMAIL) {
            UserEmail("wook@gmail.c")
        }
    }
}

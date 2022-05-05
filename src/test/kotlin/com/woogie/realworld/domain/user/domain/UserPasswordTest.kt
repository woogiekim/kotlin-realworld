package com.woogie.realworld.domain.user.domain

import com.woogie.realworld.exception.ErrorCode.*
import com.woogie.realworld.support.assertThatRealWorldException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserPasswordTest {
    @Test
    fun `UserPassword 생성 성공`() {
        val password = UserPassword("1234567")

        assertThat(password).isEqualTo(UserPassword("1234567"))
    }

    @Test
    fun `공백이 있으면 생성 실패`() {
        assertThatRealWorldException(REQUIRED) {
            UserPassword(" ")
        }
    }

    @Test
    fun `최대길이 보다 크면 생성 실패`() {
        val password = (0 until UserPassword.MAXIMUM_LENGTH + 1).joinToString("")

        assertThatRealWorldException(INVALID_USER_PASSWORD_MAXIMUM_LENGTH) {
            UserPassword(password)
        }
    }

    @Test
    fun `최소길이 보다 작으면 생성 실패`() {
        val password = (0 until UserPassword.MINIMUM_LENGTH - 1).joinToString("")

        assertThatRealWorldException(INVALID_USER_PASSWORD_MINIMUM_LENGTH) {
            UserPassword(password)
        }
    }
}

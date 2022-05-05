package com.woogie.realworld.domain.user.domain

import com.woogie.realworld.exception.ErrorCode
import com.woogie.realworld.exception.ErrorCode.REQUIRED
import com.woogie.realworld.support.assertThatRealWorldException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UsernameTest {
    @Test
    fun `Username 생성 성공`() {
        val username = Username("김태욱")

        assertThat(username).isEqualTo(Username("김태욱"))
    }

    @Test
    fun `공백이 있으면 생성 실패`() {
        assertThatRealWorldException(REQUIRED) {
            Username(" ")
        }
    }

    @Test
    fun `최대길이 보다 크면 생성 실패`() {
        val name = (0 until Username.MAXIMUM_LENGTH + 1).joinToString("")

        assertThatRealWorldException(ErrorCode.INVALID_USERNAME_MAXIMUM_LENGTH) {
            Username(name)
        }
    }
}

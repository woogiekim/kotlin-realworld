package com.woogie.realworld.domain.user.domain

import com.woogie.realworld.exception.RealWorldException
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserImageTest {
    @Test
    fun `UserImage 생성 성공`() {
        val image = UserImage("image.png")

        assertThat(image).isEqualTo(UserImage("image.png"))
    }

    @Test
    fun `최대길이 보다 크면 생성 실패`() {
        val image = (0 until UserImage.MAXIMUM_LENGTH + 1).joinToString("")

        Assertions.assertThatExceptionOfType(RealWorldException::class.java)
            .isThrownBy { UserImage(image) }
    }
}

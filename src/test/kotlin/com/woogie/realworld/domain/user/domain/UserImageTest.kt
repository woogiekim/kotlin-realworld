package com.woogie.realworld.domain.user.domain

import com.woogie.realworld.exception.ErrorCode.INVALID_USER_IMAGE_MAXIMUM_LENGTH
import com.woogie.realworld.support.assertThatRealWorldException
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

        assertThatRealWorldException(INVALID_USER_IMAGE_MAXIMUM_LENGTH) {
            UserImage(image)
        }
    }
}

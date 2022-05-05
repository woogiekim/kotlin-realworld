package com.woogie.realworld.domain.tag.domain

import com.woogie.realworld.exception.ErrorCode.INVALID_TAG_MAXIMUM_LENGTH
import com.woogie.realworld.exception.ErrorCode.REQUIRED
import com.woogie.realworld.support.assertThatRealWorldException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TagTest {

    @Test
    fun `Tag 생성 성공`() {
        val tag = Tag("tag")

        assertThat(tag.name).isEqualTo("tag")
    }

    @Test
    fun `공백이 있으면 생성 실패`() {
        assertThatRealWorldException(REQUIRED) { Tag(" ") }
    }

    @Test
    fun `최대길이 보다 크면 생성 실패`() {
        val tag = (0 until Tag.MAXIMUM_LENGTH + 1).joinToString("")

        assertThatRealWorldException(INVALID_TAG_MAXIMUM_LENGTH) { Tag(tag) }
    }
}

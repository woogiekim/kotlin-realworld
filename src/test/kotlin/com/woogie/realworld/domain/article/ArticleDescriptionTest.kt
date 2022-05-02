package com.woogie.realworld.domain.article

import com.woogie.realworld.exception.ErrorCode
import com.woogie.realworld.support.assertThatRealWorldException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ArticleDescriptionTest {

    @Test
    fun `ArticleDescription 생성 성공`() {
        val description = ArticleDescription("description")

        assertThat(description).isEqualTo(ArticleDescription("description"))
    }

    @Test
    fun `공백이 있으면 생성 실패`() {
        assertThatRealWorldException(ErrorCode.REQUIRED) { ArticleDescription(" ") }
    }
}

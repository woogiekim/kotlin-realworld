package com.woogie.realworld.domain.article.domain

import com.woogie.realworld.exception.ErrorCode.REQUIRED
import com.woogie.realworld.support.assertThatRealWorldException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ArticleBodyTest {

    @Test
    fun `ArticleBody 생성 성공`() {
        val body = ArticleBody("body")

        assertThat(body).isEqualTo(ArticleBody("body"))
    }

    @Test
    fun `공백이 있으면 생성 실패`() {
        assertThatRealWorldException(REQUIRED) { ArticleBody(" ") }
    }
}

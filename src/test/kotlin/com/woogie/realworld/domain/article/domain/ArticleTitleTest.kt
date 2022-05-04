package com.woogie.realworld.domain.article.domain

import com.woogie.realworld.exception.ErrorCode.INVALID_ARTICLE_MINIMUM_LENGTH
import com.woogie.realworld.exception.ErrorCode.REQUIRED
import com.woogie.realworld.support.assertThatRealWorldException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ArticleTitleTest {

    @Test
    fun `ArticleTitle 생성 성공`() {
        val title = ArticleTitle("title")

        assertThat(title).isEqualTo(ArticleTitle("title"))
    }

    @Test
    fun `공백이 있으면 생성 실패`() {
        assertThatRealWorldException(REQUIRED) { ArticleTitle(" ") }
    }

    @Test
    fun `최대길이 보다 크면 생성 실패`() {
        val title = (0 until ArticleTitle.MAXIMUM_LENGTH + 1).joinToString("")

        assertThatRealWorldException(INVALID_ARTICLE_MINIMUM_LENGTH) { ArticleTitle(title) }
    }
}

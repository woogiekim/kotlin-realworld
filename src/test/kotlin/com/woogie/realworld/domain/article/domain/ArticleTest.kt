package com.woogie.realworld.domain.article.domain

import com.woogie.realworld.domain.article.domain.Article
import com.woogie.realworld.domain.tag.Tag
import com.woogie.realworld.fixture.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ArticleTest {

    @Test
    fun `Article 생성 성공`() {
        val article = Article(
            createUser(), createArticleTitle(), createArticleSlug(), createArticleDescription(), createArticleBody(),
            mutableListOf(Tag("tag1"), Tag("tag2"))
        )

        assertThat(article.title).isEqualTo(createArticleTitle())
        assertThat(article.description).isEqualTo(createArticleDescription())
        assertThat(article.body).isEqualTo(createArticleBody())
        assertThat(article.tags).containsExactly(Tag("tag1"), Tag("tag2"))
    }
}

package com.woogie.realworld.domain.article

import com.woogie.realworld.fixture.createArticle
import com.woogie.realworld.support.BaseRepositoryTest
import com.woogie.realworld.support.findByIdOrThrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class ArticleRepositoryTest @Autowired constructor(
    private val articleRepository: ArticleRepository
) : BaseRepositoryTest() {

    @Test
    fun `Article 생성 성공`() {
        val article = articleRepository.save(createArticle())

        assertThat(article.id).isNotNull

        val foundArticle = articleRepository.findByIdOrThrow(article.id)

        assertThat(foundArticle).isEqualTo(article)
    }
}

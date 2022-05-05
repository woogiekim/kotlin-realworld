package com.woogie.realworld.domain.article.service

import com.woogie.realworld.domain.article.domain.ArticleRepository
import com.woogie.realworld.domain.tag.domain.Tag
import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.service.UserRegistrationUseCase
import com.woogie.realworld.fixture.createArticleBody
import com.woogie.realworld.fixture.createArticleDescription
import com.woogie.realworld.fixture.createArticleTitle
import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.BaseServiceTest
import com.woogie.realworld.support.findByIdOrThrow
import org.assertj.core.api.Assertions
import org.assertj.core.groups.Tuple.tuple
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.EntityManager

internal class ArticleRegistrationCommandTest @Autowired constructor(
    private val articleRegistrationUseCase: ArticleRegistrationUseCase,
    private val userRegistrationUseCase: UserRegistrationUseCase,
    private val articleRepository: ArticleRepository,
    private val entityManager: EntityManager
) : BaseServiceTest() {

    private lateinit var user: User

    @BeforeEach
    fun setUp() {
        user = userRegistrationUseCase.register(createUser())
    }

    @Test
    fun `아티클 등록`() {
        val article = articleRegistrationUseCase.register(
            user.id!!, createArticleTitle(), createArticleDescription(), createArticleBody(),
            listOf(Tag("tag1"), Tag("tag2"))
        )

        entityManager.flush()
        entityManager.clear()

        val foundArticle = articleRepository.findByIdOrThrow(article.id)

        Assertions.assertThat(foundArticle.tags)
            .extracting(Tag::name)
            .containsExactly(tuple("tag1"), tuple("tag2"))
    }
}

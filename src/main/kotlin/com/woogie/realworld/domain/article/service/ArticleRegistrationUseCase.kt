package com.woogie.realworld.domain.article.service

import com.woogie.realworld.domain.article.domain.*
import com.woogie.realworld.domain.tag.domain.Tag
import com.woogie.realworld.domain.tag.service.TagRevisionUseCase
import com.woogie.realworld.domain.user.service.GetUserQuery
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface ArticleRegistrationUseCase {
    /**
     * 아티클 등록
     */
    fun register(
        authorId: Long, title: ArticleTitle, description: ArticleDescription, body: ArticleBody, tags: List<Tag>?
    ): Article
}

@Service
@Transactional
class ArticleRegistrationCommand(
    private val articleRepository: ArticleRepository,
    private val getUserQuery: GetUserQuery,
    private val tagRevisionUseCase: TagRevisionUseCase
) : ArticleRegistrationUseCase {
    override fun register(
        authorId: Long, title: ArticleTitle, description: ArticleDescription, body: ArticleBody, tags: List<Tag>?
    ): Article {
        val author = getUserQuery.getUser(authorId)

        val article = Article(author, title, ArticleSlug.create(title), description, body)

        tags?.forEach { tag ->
            val amendedTag = tagRevisionUseCase.amend(tag)

            article.withTag(amendedTag)
        }

        return articleRepository.save(article)
    }
}

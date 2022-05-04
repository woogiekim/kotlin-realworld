package com.woogie.realworld.domain.article.domain

import com.woogie.realworld.exception.ErrorCode.INVALID_ARTICLE_MINIMUM_LENGTH
import com.woogie.realworld.exception.ErrorCode.REQUIRED
import com.woogie.realworld.exception.validate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class ArticleTitle(
    @Column(name = "title", length = MAXIMUM_LENGTH)
    val value: String
) {
    init {
        validate(value.isNotBlank()) { REQUIRED }
        validate(value.length <= MAXIMUM_LENGTH) { INVALID_ARTICLE_MINIMUM_LENGTH }
    }

    companion object {
        const val MAXIMUM_LENGTH = 300
    }
}

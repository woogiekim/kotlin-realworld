package com.woogie.realworld.domain.article.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class ArticleSlug(
    @Column(name = "slug")
    val value: String
) {
    companion object {
        fun create(title: ArticleTitle): ArticleSlug {
            return ArticleSlug(title.value.lowercase().replace(" ", "-"))
        }
    }
}

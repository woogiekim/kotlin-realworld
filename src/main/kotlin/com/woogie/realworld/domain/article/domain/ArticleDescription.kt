package com.woogie.realworld.domain.article.domain

import com.woogie.realworld.exception.ErrorCode.REQUIRED
import com.woogie.realworld.exception.validate
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Lob

@Embeddable
data class ArticleDescription(
    @Lob
    @Column(name = "description")
    val value: String
) {
    init {
        validate(this.value.isNotBlank()) { REQUIRED }
    }
}

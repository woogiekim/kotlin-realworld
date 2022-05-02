package com.woogie.realworld.domain.tag

import com.woogie.realworld.exception.ErrorCode.REQUIRED
import com.woogie.realworld.exception.validate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Tag(
    @Column(name = "tag", length = MAX_LENGTH)
    val value: String
) {
    init {
        validate(value.isNotBlank()) { REQUIRED }
    }

    companion object {
        const val MAX_LENGTH = 100
    }
}

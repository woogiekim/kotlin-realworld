package com.woogie.realworld.domain.tag.domain

import com.woogie.realworld.exception.ErrorCode.INVALID_TAG_MAXIMUM_LENGTH
import com.woogie.realworld.exception.ErrorCode.REQUIRED
import com.woogie.realworld.exception.validate
import com.woogie.realworld.support.BaseAggregateRoot
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Tag(
    @Column(length = MAXIMUM_LENGTH)
    val name: String
) : BaseAggregateRoot<Tag>() {
    init {
        validate(this.name.isNotBlank()) { REQUIRED }
        validate(this.name.length <= MAXIMUM_LENGTH) { INVALID_TAG_MAXIMUM_LENGTH }
    }

    companion object {
        const val MAXIMUM_LENGTH = 100
    }
}

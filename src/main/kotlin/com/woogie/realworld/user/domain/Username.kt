package com.woogie.realworld.user.domain

import com.woogie.realworld.exception.ErrorCode.INVALID_USERNAME_MAXIMUM_LENGTH
import com.woogie.realworld.exception.ErrorCode.REQUIRED
import com.woogie.realworld.exception.validate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Username(
    @Column(length = MAXIMUM_LENGTH)
    val username: String
) {
    init {
        validate(username.isNotBlank()) { REQUIRED }
        validate(username.length <= MAXIMUM_LENGTH) { INVALID_USERNAME_MAXIMUM_LENGTH }
    }

    override fun toString(): String {
        return username
    }

    companion object {
        const val MAXIMUM_LENGTH = 100
    }
}

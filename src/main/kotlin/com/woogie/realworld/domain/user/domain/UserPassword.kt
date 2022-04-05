package com.woogie.realworld.domain.user.domain

import com.woogie.realworld.exception.ErrorCode.*
import com.woogie.realworld.exception.validate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class UserPassword(
    @Column(length = MAXIMUM_LENGTH)
    val password: String
) {
    init {
        validate(password.isNotBlank()) { REQUIRED }
        validate(password.length >= MINIMUM_LENGTH) { INVALID_USER_PASSWORD_MINIMUM_LENGTH }
        validate(password.length <= MAXIMUM_LENGTH) { INVALID_USER_PASSWORD_MAXIMUM_LENGTH }
    }

    override fun toString(): String {
        return password
    }

    companion object {
        const val MINIMUM_LENGTH = 7
        const val MAXIMUM_LENGTH = 100
    }
}

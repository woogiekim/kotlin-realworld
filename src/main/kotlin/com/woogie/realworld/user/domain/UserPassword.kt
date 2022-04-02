package com.woogie.realworld.user.domain

import com.woogie.realworld.exception.ErrorCode.INVALID_USER_PASSWORD_LENGTH
import com.woogie.realworld.exception.validate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class UserPassword(
    @Column(length = 100)
    val password: String
) {
    init {
        validate(password.length >= MINIMUM_LENGTH) { INVALID_USER_PASSWORD_LENGTH }
    }

    override fun toString(): String {
        return password
    }

    companion object {
        const val MINIMUM_LENGTH = 7
    }
}

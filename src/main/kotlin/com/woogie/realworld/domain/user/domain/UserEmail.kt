package com.woogie.realworld.domain.user.domain

import com.woogie.realworld.exception.ErrorCode.*
import com.woogie.realworld.exception.validate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class UserEmail(
    @Column(length = MAXIMUM_LENGTH, unique = true)
    val email: String
) {
    init {
        validate(email.isNotBlank()) { REQUIRED }
        validate(email.matches(Regex(EMAIL_REGEX))) { INVALID_EMAIL }
        validate(email.length <= MAXIMUM_LENGTH) { INVALID_EMAIL_MAXIMUM_LENGTH }
    }

    override fun toString(): String {
        return email
    }

    companion object {
        const val MAXIMUM_LENGTH = 100
        const val EMAIL_REGEX = "^([\\w_.\\-+])+@([\\w\\-]+\\.)+([\\w]{2,10})+\$"
    }
}

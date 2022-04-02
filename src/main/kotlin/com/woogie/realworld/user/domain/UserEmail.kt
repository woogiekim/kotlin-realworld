package com.woogie.realworld.user.domain

import com.woogie.realworld.exception.ErrorCode.INVALID_EMAIL
import com.woogie.realworld.exception.validate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class UserEmail(
    @Column(length = 100, unique = true)
    val email: String
) {
    init {
        validate(email.matches(Regex(EMAIL_REGEX))) { INVALID_EMAIL }
    }

    override fun toString(): String {
        return email
    }

    companion object {
        const val EMAIL_REGEX = "^([\\w_.\\-+])+@([\\w\\-]+\\.)+([\\w]{2,10})+\$"
    }
}

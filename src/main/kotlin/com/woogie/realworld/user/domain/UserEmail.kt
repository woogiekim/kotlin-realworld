package com.woogie.realworld.user.domain

import com.woogie.realworld.exception.ErrorCode.INVALID_EMAIL
import com.woogie.realworld.exception.validate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class UserEmail(
    @Column(name = "email", length = 100, unique = true)
    val value: String
) {
    init {
        validate(value.matches(Regex(EMAIL_REGEX))) { INVALID_EMAIL }
    }

    companion object {
        const val EMAIL_REGEX = "^([\\w_.\\-+])+@([\\w\\-]+\\.)+([\\w]{2,10})+\$"
    }
}

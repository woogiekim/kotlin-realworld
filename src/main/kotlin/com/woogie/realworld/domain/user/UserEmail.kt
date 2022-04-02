package com.woogie.realworld.domain.user

import com.woogie.realworld.exception.ErrorCode.INVALID_EMAIL
import com.woogie.realworld.exception.validate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class UserEmail(
    @Column(unique = true)
    val email: String
) {
    init {
        validate(email.matches(Regex(EMAIL_REGEX))) { INVALID_EMAIL }
    }

    companion object {
        const val EMAIL_REGEX = "^([\\w_.\\-+])+@([\\w\\-]+\\.)+([\\w]{2,10})+\$"
    }
}

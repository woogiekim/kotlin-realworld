package com.woogie.realworld.user.domain

import javax.persistence.Embeddable
import javax.persistence.Lob

@Embeddable
data class UserBio(
    @Lob
    val bio: String
) {
    override fun toString(): String {
        return bio
    }
}

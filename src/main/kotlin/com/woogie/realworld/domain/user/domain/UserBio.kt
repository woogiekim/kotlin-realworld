package com.woogie.realworld.domain.user.domain

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

    companion object {
        fun createOrNull(bio: String?): UserBio? {
            return bio?.let { UserBio(bio) }
        }
    }
}

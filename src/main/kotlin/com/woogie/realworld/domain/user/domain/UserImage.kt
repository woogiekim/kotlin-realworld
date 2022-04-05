package com.woogie.realworld.domain.user.domain

import com.woogie.realworld.exception.ErrorCode.INVALID_USER_IMAGE_MAXIMUM_LENGTH
import com.woogie.realworld.exception.validate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class UserImage(
    @Column(length = MAXIMUM_LENGTH)
    val image: String
) {
    init {
        validate(image.length <= MAXIMUM_LENGTH) { INVALID_USER_IMAGE_MAXIMUM_LENGTH }
    }

    override fun toString(): String {
        return image
    }

    companion object {
        const val MAXIMUM_LENGTH = 300

        fun createOrNull(image: String?): UserImage? {
            return image?.let { UserImage(it) }
        }
    }
}

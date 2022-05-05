@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.woogie.realworld.exception

import com.woogie.realworld.domain.article.domain.ArticleTitle
import com.woogie.realworld.domain.tag.domain.Tag
import com.woogie.realworld.domain.user.domain.UserEmail
import com.woogie.realworld.domain.user.domain.UserImage
import com.woogie.realworld.domain.user.domain.UserPassword
import com.woogie.realworld.domain.user.domain.Username
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class RealWorldException(errorCode: ErrorCode) : RuntimeException(errorCode.message)

enum class ErrorCode(
    val message: String
) {
    REQUIRED("필수값입니다."),

    /** 이메일 **/
    INVALID_EMAIL("이메일 형식이 아닙니다."),
    INVALID_EMAIL_MAXIMUM_LENGTH("사용자 이메일 최대 길이 ${UserEmail.MAXIMUM_LENGTH} 보다 큽니다."),

    /** 사용자 이름 **/
    INVALID_USERNAME_MAXIMUM_LENGTH("사용자 이름 최소 길이 ${Username.MAXIMUM_LENGTH} 보다 큽니다."),

    /** 사용자 비밀번호 **/
    INVALID_USER_PASSWORD_MINIMUM_LENGTH("사용자 비밀번호 최소 길이 ${UserPassword.MINIMUM_LENGTH} 보다 작습니다."),
    INVALID_USER_PASSWORD_MAXIMUM_LENGTH("사용자 비밀번호 최대 길이 ${UserPassword.MAXIMUM_LENGTH} 보다 큽니다."),

    /** 사용자 이미지 **/
    INVALID_USER_IMAGE_MAXIMUM_LENGTH("사용자 이미지 최대 길이 ${UserImage.MAXIMUM_LENGTH} 보다 큽니다."),

    /** 아티클 제목 **/
    INVALID_ARTICLE_MINIMUM_LENGTH("아티클 제목 최대 길이 ${ArticleTitle.MAXIMUM_LENGTH} 보다 큽니다."),

    /** 태그 **/
    INVALID_TAG_MAXIMUM_LENGTH("태그 이름 최대 길이 ${Tag.MAXIMUM_LENGTH} 보다 큽니다.")
}

/**
 * Throws an [RealWorldException] with the result of calling [lazyMessage] if the [value] is false.
 */
@OptIn(ExperimentalContracts::class)
fun validate(value: Boolean, lazyErrorCode: () -> ErrorCode) {
    contract {
        returns() implies value
    }
    if (!value) {
        val errorCode = lazyErrorCode()
        throw RealWorldException(errorCode)
    }
}

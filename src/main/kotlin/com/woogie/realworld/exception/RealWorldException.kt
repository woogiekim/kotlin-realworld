@file:OptIn(ExperimentalContracts::class)

package com.woogie.realworld.exception

import com.woogie.realworld.exception.ErrorCode.REQUIRED
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class RealWorldException(errorCode: ErrorCode) : RuntimeException(errorCode.message)

enum class ErrorCode(
    val message: String
) {
    REQUIRED("필수값입니다."),
    INVALID_EMAIL("이메일 형식이 아닙니다."),
    INVALID_USER_PASSWORD_LENGTH("사용자 비밀번호 최소 길이보다 작습니다.")
}

/**
 * Throws an [RealWorldException] with the result of calling [lazyMessage] if the [value] is false.
 */
fun validate(value: Boolean, lazyErrorCode: () -> ErrorCode) {
    contract {
        returns() implies value
    }
    if (!value) {
        val errorCode = lazyErrorCode()
        throw RealWorldException(errorCode)
    }
}

/**
 * Throws an [RealWorldException] if the [value] is null. Otherwise returns the not null value.
 */
fun <T> validateNotNull(value: T?): T {
    contract {
        returns() implies (value != null)
    }

    if (value == null) {
        throw RealWorldException(REQUIRED)
    } else {
        return value
    }
}

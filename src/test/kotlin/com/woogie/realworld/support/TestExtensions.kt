package com.woogie.realworld.support

import com.woogie.realworld.exception.ErrorCode
import com.woogie.realworld.exception.RealWorldException
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.assertj.core.api.ThrowableAssert.ThrowingCallable

fun assertThatRealWorldException(errorCode: ErrorCode, throwingCallable: ThrowingCallable) {
    assertThatExceptionOfType(RealWorldException::class.java)
        .isThrownBy(throwingCallable)
        .withMessage(errorCode.message)
}

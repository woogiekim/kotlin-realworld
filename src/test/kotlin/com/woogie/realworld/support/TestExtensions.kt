package com.woogie.realworld.support

import com.woogie.realworld.exception.ErrorCode
import com.woogie.realworld.exception.RealWorldException
import org.assertj.core.api.Assertions
import org.assertj.core.api.ThrowableAssert.ThrowingCallable

fun assertThatRealWorldException(errorCode: ErrorCode, throwingCallable: ThrowingCallable) {
    Assertions.assertThatExceptionOfType(RealWorldException::class.java)
        .isThrownBy(throwingCallable)
        .withMessage(errorCode.message)
}

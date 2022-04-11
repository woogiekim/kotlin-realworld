package com.woogie.realworld.controller.dto

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.domain.UserEmail
import com.woogie.realworld.domain.user.domain.UserPassword

class LoginRequest(
    val user: LoginData
) {
    val email: UserEmail = UserEmail(user.email)
    val password: UserPassword = UserPassword(user.password)
}

class LoginData(
    val email: String,
    val password: String
)

class LoginResponse(
    user: User,
    token: String
) {
    val user: UserResponse = UserResponse(user, token)
}

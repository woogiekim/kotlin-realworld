package com.woogie.realworld.controller.dto

import com.woogie.realworld.user.domain.User
import com.woogie.realworld.user.domain.UserEmail
import com.woogie.realworld.user.domain.UserPassword
import com.woogie.realworld.user.domain.Username

class UserRegistrationRequest(
    val user: UserRegistrationData
) {
    fun toEntity(): User {
        return with(user) {
            User(Username(username), UserEmail(email), UserPassword(password))
        }
    }
}

class UserRegistrationData(
    val username: String,
    val email: String,
    val password: String
)

class UserRegistrationResponse(
    user: User
) {
    val user: UserResponse = UserResponse(user)
}

package com.woogie.realworld.controller.dto

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.domain.UserEmail
import com.woogie.realworld.domain.user.domain.UserPassword
import com.woogie.realworld.domain.user.domain.Username

class UserRegistrationRequest(
    val user: UserRegistrationData
) {
    fun toEntity(): User {
        return with(user) {
            User.create(UserEmail(email), UserPassword(password), Username(username))
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

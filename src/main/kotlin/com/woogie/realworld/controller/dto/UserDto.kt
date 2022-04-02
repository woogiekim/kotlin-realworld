package com.woogie.realworld.controller.dto

import com.woogie.realworld.user.domain.User
import com.woogie.realworld.user.domain.UserEmail
import com.woogie.realworld.user.domain.UserPassword
import com.woogie.realworld.user.domain.Username

data class UserRegisterRequest(
    val username: Username,
    val email: UserEmail,
    val password: UserPassword
) {
    fun toEntity(): User {
        return User(username, email, password)
    }
}

class UserRegisterResponse(
    user: User
) {
    val user: UserResponse = UserResponse(user)
}

class UserResponse(
    user: User
) {
    val email: String = user.email.toString()
    val token: String? = null
    val username: String = user.name.toString()
    val bio: String? = null
    val image: String? = null
}

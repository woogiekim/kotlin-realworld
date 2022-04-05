package com.woogie.realworld.controller.dto

import com.woogie.realworld.domain.user.domain.User

class UserResponse(
    user: User,
    val token: String? = null
) {
    val email: String = user.email.toString()
    val username: String = user.username.toString()
    val bio: String? = user.bio?.toString()
    val image: String? = user.image?.toString()
}

class CurrentUserResponse(
    user: User
) {
    val user: UserResponse = UserResponse(user)
}

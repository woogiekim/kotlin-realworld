package com.woogie.realworld.controller.dto

import com.woogie.realworld.domain.user.domain.User

class UserUpdateRequest(
    val user: UserUpdateData
)

class UserUpdateData(
    val username: String,
    val email: String,
    val password: String,
    val image: String?,
    val bio: String?
)

class UserUpdateResponse(
    user: User
) {
    val user = UserResponse(user)
}

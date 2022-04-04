package com.woogie.realworld.controller.dto

import com.woogie.realworld.user.domain.Profile

class GetProfileResponse(
    profile: Profile
) {
    val profile = ProfileResponse(profile)
}

class ProfileResponse(
    profile: Profile,
    val following: Boolean = false
) {
    val username: String = profile.username.toString()
    val bio: String? = profile.bio?.toString()
    val image: String? = profile.image?.toString()
}

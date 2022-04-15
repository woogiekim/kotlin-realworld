package com.woogie.realworld.controller

import com.woogie.realworld.controller.dto.GetProfileResponse
import com.woogie.realworld.domain.user.domain.Username
import com.woogie.realworld.domain.user.service.GetProfileQuery
import com.woogie.realworld.domain.user.service.UserFollowUseCase
import com.woogie.realworld.security.SecurityUser
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/profile")
class ProfileApi(
    private val getProfileQuery: GetProfileQuery,
    private val userFollowUseCase: UserFollowUseCase
) {

    @GetMapping("/{username}")
    fun getProfile(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @PathVariable username: Username
    ): GetProfileResponse {
        val (profile, following) = getProfileQuery.getProfile(username, securityUser.id)

        return GetProfileResponse(profile, following)
    }

    @PostMapping("/{followerId}/follow")
    fun follow(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @PathVariable followerId: Long
    ): GetProfileResponse {
        val (followee, following) = userFollowUseCase.follow(Username(securityUser.username), followerId)

        return GetProfileResponse(followee.profile, following)
    }
}

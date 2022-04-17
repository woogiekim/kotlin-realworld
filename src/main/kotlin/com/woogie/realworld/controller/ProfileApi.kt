package com.woogie.realworld.controller

import com.woogie.realworld.controller.dto.GetProfileResponse
import com.woogie.realworld.domain.user.domain.Username
import com.woogie.realworld.domain.user.service.GetProfileQuery
import com.woogie.realworld.domain.user.service.FollowUseCase
import com.woogie.realworld.domain.user.service.UnFollowUseCase
import com.woogie.realworld.security.SecurityUser
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/profile")
class ProfileApi(
    private val getProfileQuery: GetProfileQuery,
    private val followUseCase: FollowUseCase,
    private val unFollowUseCase: UnFollowUseCase
) {

    @GetMapping("/{username}")
    fun getProfile(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @PathVariable username: Username
    ): GetProfileResponse {
        val (profile, following) = getProfileQuery.getProfile(username, securityUser.id)

        return GetProfileResponse(profile, following)
    }

    @PostMapping("/{username}/follow")
    fun follow(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @PathVariable username: String
    ): GetProfileResponse {
        val (followee, following) = followUseCase.followOrUnFollow(Username(username), securityUser.id!!)

        return GetProfileResponse(followee.profile, following)
    }
}

package com.woogie.realworld.controller

import com.woogie.realworld.controller.dto.GetProfileResponse
import com.woogie.realworld.domain.user.domain.Username
import com.woogie.realworld.domain.user.service.GetProfileQuery
import com.woogie.realworld.domain.user.service.UserFollowUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/profile")
class ProfileApi(
    private val getProfileQuery: GetProfileQuery,
    private val userFollowUseCase: UserFollowUseCase
) {

    // TODO: 2022/04/03 시큐리티 설정 추가 한 이후에는 SecurityContextHolder 에서 로그인한 사용자 아이디 가져오도록 변경 필요함
    @GetMapping("/{username}/{currentUserId}")
    fun getProfile(@PathVariable username: Username, @PathVariable currentUserId: Long?): GetProfileResponse {
        val (profile, following) = getProfileQuery.getProfile(username, currentUserId)

        return GetProfileResponse(profile, following)
    }

    // TODO: 2022/04/03 시큐리티 설정 추가 한 이후에는 SecurityContextHolder 에서 로그인한 사용자 아이디 가져오도록 변경 필요함
    @PostMapping("/{username}/{followerId}/follow")
    fun follow(@PathVariable username: Username, @PathVariable followerId: Long): GetProfileResponse {
        val (followee, following) = userFollowUseCase.follow(username, followerId)

        return GetProfileResponse(followee.profile, following)
    }
}

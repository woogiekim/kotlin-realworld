package com.woogie.realworld.controller

import com.woogie.realworld.controller.dto.GetProfileResponse
import com.woogie.realworld.user.service.GetProfileQuery
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/profile")
class ProfileApi(
    private val getProfileQuery: GetProfileQuery
) {

    @GetMapping("/{id}")
    fun getProfile(@PathVariable id: Long): GetProfileResponse {
        val profile = getProfileQuery.getProfile(id)

        return GetProfileResponse(profile)
    }
}

package com.woogie.realworld.controller

import com.woogie.realworld.controller.dto.CurrentUserResponse
import com.woogie.realworld.controller.dto.UserUpdateRequest
import com.woogie.realworld.controller.dto.UserUpdateResponse
import com.woogie.realworld.domain.user.domain.*
import com.woogie.realworld.domain.user.service.GetUserQuery
import com.woogie.realworld.domain.user.service.UserUpdateUseCase
import com.woogie.realworld.security.SecurityUser
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserApi(
    private val getUserQuery: GetUserQuery,
    private val userUpdateUseCase: UserUpdateUseCase
) {

    /**
     * 현재 사용자 조회
     */
    @GetMapping
    fun getCurrent(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @RequestHeader(AUTHORIZATION) token: String
    ): CurrentUserResponse {
        val user = getUserQuery.getUser(securityUser.id!!)

        return CurrentUserResponse(user, token)
    }

    /**
     * 사용자 수정
     */
    @PutMapping
    fun update(
        @AuthenticationPrincipal securityUser: SecurityUser,
        @RequestBody req: UserUpdateRequest
    ): UserUpdateResponse {
        val user = with(req.user) {
            userUpdateUseCase.update(
                securityUser.id!!,
                Username(username), UserEmail(email), UserPassword(password),
                UserImage.createOrNull(image), UserBio.createOrNull(bio)
            )
        }

        return UserUpdateResponse(user)
    }
}

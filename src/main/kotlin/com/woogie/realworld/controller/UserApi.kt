package com.woogie.realworld.controller

import com.woogie.realworld.controller.dto.*
import com.woogie.realworld.user.domain.*
import com.woogie.realworld.user.service.GetUserQuery
import com.woogie.realworld.user.service.UserRegistrationUseCase
import com.woogie.realworld.user.service.UserUpdateUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserApi(
    private val getUserQuery: GetUserQuery,
    private val userRegistrationUseCase: UserRegistrationUseCase,
    private val userUpdateUseCase: UserUpdateUseCase
) {

    /**
     * 사용자 등록
     */
    @PostMapping
    fun register(@RequestBody req: UserRegistrationRequest): UserRegistrationResponse {
        val user = userRegistrationUseCase.register(req.toEntity())

        return UserRegistrationResponse(user)
    }

    /**
     * 현재 사용자 조회
     */
    // TODO: 2022/04/03 시큐리티 설정 추가 한 이후에는 SecurityContextHolder 에서 로그인한 사용자 아이디 가져오도록 변경 필요함
    @GetMapping("/{id}")
    fun getCurrent(@PathVariable id: Long): CurrentUserResponse {
        val user = getUserQuery.getUser(id)

        return CurrentUserResponse(user)
    }

    /**
     * 사용자 수정
     */
    // TODO: 2022/04/03 시큐리티 설정 추가 한 이후에는 SecurityContextHolder 에서 로그인한 사용자 아이디 가져오도록 변경 필요함
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody req: UserUpdateRequest): UserUpdateResponse {
        val user = with(req.user) {
            userUpdateUseCase.update(id,
                Username(username), UserEmail(email), UserPassword(password),
                UserImage.createOrNull(image), UserBio.createOrNull(bio)
            )
        }

        return UserUpdateResponse(user)
    }
}

package com.woogie.realworld.controller

import com.woogie.realworld.controller.dto.CurrentUserResponse
import com.woogie.realworld.controller.dto.UserRegisterRequest
import com.woogie.realworld.controller.dto.UserRegisterResponse
import com.woogie.realworld.user.service.GetUserQuery
import com.woogie.realworld.user.service.UserRegistrationUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserApi(
    private val userRegistrationUseCase: UserRegistrationUseCase,
    private val getUserQuery: GetUserQuery
) {

    /**
     * 사용자 등록
     */
    @PostMapping
    fun register(@RequestBody req: UserRegisterRequest): UserRegisterResponse {
        val user = userRegistrationUseCase.register(req.toEntity())

        return UserRegisterResponse(user)
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
}

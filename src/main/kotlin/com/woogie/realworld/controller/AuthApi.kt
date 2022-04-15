package com.woogie.realworld.controller

import com.woogie.realworld.controller.dto.LoginRequest
import com.woogie.realworld.controller.dto.LoginResponse
import com.woogie.realworld.controller.dto.UserRegistrationRequest
import com.woogie.realworld.controller.dto.UserRegistrationResponse
import com.woogie.realworld.domain.user.service.LoginUseCase
import com.woogie.realworld.domain.user.service.UserRegistrationUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class AuthApi(
    private val userRegistrationUseCase: UserRegistrationUseCase,
    private val loginUseCase: LoginUseCase
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
     * 사용자 로그인
     */
    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): LoginResponse {
        val (user, token) = loginUseCase.login(req.email, req.password)

        return LoginResponse(user, token)
    }
}

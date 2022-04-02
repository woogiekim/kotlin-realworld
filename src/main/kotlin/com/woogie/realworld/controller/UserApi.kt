package com.woogie.realworld.controller

import com.woogie.realworld.controller.dto.UserRegisterRequest
import com.woogie.realworld.controller.dto.UserRegisterResponse
import com.woogie.realworld.user.service.UserRegisterUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserApi(
    private val userRegister: UserRegisterUseCase
) {

    @PostMapping("/register")
    fun register(@RequestBody req: UserRegisterRequest): UserRegisterResponse {
        val user = userRegister.register(req.toEntity())

        return UserRegisterResponse(user)
    }
}

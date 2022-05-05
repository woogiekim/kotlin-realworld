package com.woogie.realworld.domain.user.service

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.domain.UserEmail
import com.woogie.realworld.domain.user.domain.UserPassword
import com.woogie.realworld.domain.user.domain.UserRepository
import org.springframework.stereotype.Service

interface LoginUseCase {
    /**
     * 사용자 로그인
     */
    fun login(email: UserEmail, password: UserPassword): Pair<User, String>
}

interface PasswordValidator {
    /**
     * 비밀번호 유효성 검사
     */
    fun validate(rawPassword: UserPassword, user: User): Boolean
}

interface TokenGenerator {
    /**
     * 토큰 생성
     */
    fun generate(user: User): String
}

@Service
class LoginCommand(
    private val userRepository: UserRepository,
    private val passwordValidator: PasswordValidator,
    private val tokenGenerator: TokenGenerator
) : LoginUseCase {
    override fun login(email: UserEmail, password: UserPassword): Pair<User, String> {
        val user = userRepository.findByEmail(email)!!

        passwordValidator.validate(password, user)

        val token = tokenGenerator.generate(user)

        return Pair(user, token)
    }
}

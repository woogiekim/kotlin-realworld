package com.woogie.realworld.domain.user.service

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.domain.UserEmail
import com.woogie.realworld.domain.user.domain.UserPassword
import com.woogie.realworld.domain.user.domain.UserRepository
import org.springframework.stereotype.Service

interface UserLoginUseCase {
    fun login(email: UserEmail, password: UserPassword): Pair<User, String>
}

interface PasswordValidator {
    fun validate(rawPassword: UserPassword, user: User): Boolean
}

interface TokenGenerator {
    fun generate(user: User): String
}

@Service
class UserLoginCommand(
    private val userRepository: UserRepository,
    private val passwordValidator: PasswordValidator,
    private val tokenGenerator: TokenGenerator
) : UserLoginUseCase {
    override fun login(email: UserEmail, password: UserPassword): Pair<User, String> {
        val user = userRepository.findByEmail(email)!!

        passwordValidator.validate(password, user)

        val token = tokenGenerator.generate(user)

        return Pair(user, token)
    }
}

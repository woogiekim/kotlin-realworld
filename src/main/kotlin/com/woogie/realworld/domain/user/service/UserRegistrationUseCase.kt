package com.woogie.realworld.domain.user.service

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.domain.UserPassword
import com.woogie.realworld.domain.user.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UserRegistrationUseCase {
    /**
     * 사용자 등록
     */
    fun register(user: User): User
}

interface PasswordGenerator {
    fun generate(password: UserPassword): UserPassword
}

@Service
@Transactional
class UserRegistrationCommand(
    private val userRepository: UserRepository,
    private val passwordGenerator: PasswordGenerator
) : UserRegistrationUseCase {
    override fun register(user: User): User {
        user.encodePassword(passwordGenerator)

        return userRepository.save(user)
    }
}

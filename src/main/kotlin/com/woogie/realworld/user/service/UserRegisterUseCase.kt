package com.woogie.realworld.user.service

import com.woogie.realworld.user.domain.User
import com.woogie.realworld.user.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UserRegisterUseCase {
    fun register(user: User): User
}

@Service
@Transactional
class DefaultUserRegister(
    private val userRepository: UserRepository
) : UserRegisterUseCase {
    override fun register(user: User): User {
        return userRepository.save(user)
    }
}

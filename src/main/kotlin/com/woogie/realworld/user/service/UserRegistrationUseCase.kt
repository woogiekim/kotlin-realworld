package com.woogie.realworld.user.service

import com.woogie.realworld.user.domain.User
import com.woogie.realworld.user.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UserRegistrationUseCase {
    /**
     * 사용자 등록
     */
    fun register(user: User): User
}

@Service
@Transactional
class UserRegistrationCommand(
    private val userRepository: UserRepository
) : UserRegistrationUseCase {
    override fun register(user: User): User {
        return userRepository.save(user)
    }
}

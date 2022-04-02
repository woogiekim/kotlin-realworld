package com.woogie.realworld.service

import com.woogie.realworld.domain.user.User
import com.woogie.realworld.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UserService {
    fun register(user: User): User
}

@Service
@Transactional
class DefaultUserService(
    private val userRepository: UserRepository
) : UserService {
    override fun register(user: User): User {
        return userRepository.save(user)
    }
}

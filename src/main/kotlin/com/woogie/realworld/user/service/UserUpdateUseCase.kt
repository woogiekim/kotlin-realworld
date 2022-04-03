package com.woogie.realworld.user.service

import com.woogie.realworld.support.findByIdOrThrow
import com.woogie.realworld.user.domain.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UserUpdateUseCase {
    /**
     * 사용자 수정
     */
    fun update(
        id: Long, name: Username, email: UserEmail, password: UserPassword, image: UserImage?, bio: UserBio?
    ): User
}

@Service
@Transactional
class UserUpdateCommand(
    private val userRepository: UserRepository
) : UserUpdateUseCase {
    override fun update(
        id: Long, name: Username, email: UserEmail, password: UserPassword, image: UserImage?, bio: UserBio?
    ): User {
        val user = userRepository.findByIdOrThrow(id)

        user.update(name, email, password, image, bio)

        return user
    }
}

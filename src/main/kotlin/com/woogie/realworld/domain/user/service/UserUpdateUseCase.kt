package com.woogie.realworld.domain.user.service

import com.woogie.realworld.domain.user.domain.*
import org.springframework.data.repository.findByIdOrNull
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
        val user = userRepository.findByIdOrNull(id)!!

        user.update(email, password, name, bio, image)

        return user
    }
}

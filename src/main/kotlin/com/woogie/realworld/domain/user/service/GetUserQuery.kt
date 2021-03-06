package com.woogie.realworld.domain.user.service

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.domain.UserEmail
import com.woogie.realworld.domain.user.domain.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface GetUserQuery {
    /**
     * 사용자 조회
     */
    fun getUser(id: Long): User

    /**
     * 사용자 조회
     */
    fun getUser(email: UserEmail): User
}

@Service
@Transactional
class GetUserService(
    private val userRepository: UserRepository
) : GetUserQuery {
    override fun getUser(id: Long): User {
        return userRepository.findByIdOrNull(id)!!
    }

    override fun getUser(email: UserEmail): User {
        return userRepository.findByEmail(email)!!
    }
}

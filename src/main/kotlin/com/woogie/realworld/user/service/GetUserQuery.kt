package com.woogie.realworld.user.service

import com.woogie.realworld.support.findByIdOrThrow
import com.woogie.realworld.user.domain.User
import com.woogie.realworld.user.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface GetUserQuery {
    /**
     * 사용자 조회
     */
    fun getUser(id: Long): User
}

@Service
@Transactional
class GetUserService(
    private val userRepository: UserRepository
) : GetUserQuery {
    override fun getUser(id: Long): User {
        return userRepository.findByIdOrThrow(id)
    }
}

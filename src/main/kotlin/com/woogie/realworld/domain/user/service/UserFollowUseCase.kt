package com.woogie.realworld.domain.user.service

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.domain.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UserFollowUseCase {
    /**
     * 팔로우이가 팔로워를 등록
     * followee 가 follower를 등록하는 형태이고, followee를 리턴
     */
    fun follow(followerId: Long, followeeId: Long): User
}

@Service
@Transactional
class UserFollowCommand(
    private val userRepository: UserRepository
) : UserFollowUseCase {
    override fun follow(followerId: Long, followeeId: Long): User {
        val followee = userRepository.findByIdOrNull(followeeId)!!
        val follower = userRepository.findByIdOrNull(followerId)!!

        followee.follow(follower)

        return followee
    }
}

package com.woogie.realworld.domain.user.service

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.domain.UserRepository
import com.woogie.realworld.domain.user.domain.Username
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface FollowUseCase {
    /**
     * 팔로우이가 팔로워를 등록
     * followee 가 follower를 등록하는 형태이고, followee, following(팔로우 유무) 를 리턴
     */
    fun follow(followeeName: Username, followerId: Long): Pair<User, Boolean>
}

@Service
@Transactional
class FollowCommand(
    private val userRepository: UserRepository
) : FollowUseCase {
    override fun follow(followeeName: Username, followerId: Long): Pair<User, Boolean> {
        val followee = userRepository.findByUsername(followeeName)!!
        val follower = userRepository.findByIdOrNull(followerId)!!

        followee.follow(follower)

        return Pair(followee, followee.following(follower))
    }
}

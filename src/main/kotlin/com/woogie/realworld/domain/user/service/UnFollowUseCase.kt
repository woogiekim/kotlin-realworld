package com.woogie.realworld.domain.user.service

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.domain.UserRepository
import com.woogie.realworld.domain.user.domain.Username
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UnFollowUseCase {
    /**
     * 팔로우이가 팔로워를 등록 해제
     * followee 가 follower를 등록 해제 하는 형태이고, followee, following(팔로우 유무) 를 리턴
     */
    fun unFollow(followeeName: Username, followerId: Long): Pair<User, Boolean>
}

@Service
@Transactional
class UnFollowCommand(
    private val userRepository: UserRepository
) : UnFollowUseCase {
    override fun unFollow(followeeName: Username, followerId: Long): Pair<User, Boolean> {
        val followee = userRepository.findByUsername(followeeName)!!
        val follower = userRepository.findByIdOrNull(followerId)!!

        followee.unfollow(follower)

        return Pair(followee, followee.following(follower))
    }
}

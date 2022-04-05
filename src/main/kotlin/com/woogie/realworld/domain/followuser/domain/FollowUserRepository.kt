package com.woogie.realworld.domain.followuser.domain

import com.woogie.realworld.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface FollowUserRepository : JpaRepository<FollowUser, Long> {

    fun existsByFollower(follower: User): Boolean {
        return existsByFollowersIn(listOf(follower))
    }

    fun existsByFollowersIn(followers: List<User>): Boolean
}

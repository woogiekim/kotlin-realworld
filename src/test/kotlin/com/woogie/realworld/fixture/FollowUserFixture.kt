package com.woogie.realworld.fixture

import com.woogie.realworld.domain.followuser.domain.FollowUser
import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.domain.UserEmail
import com.woogie.realworld.domain.user.domain.Username

fun createFollowUser(
    followee: User = createUser(Username("followee"), UserEmail("followee@naver.com"))
): FollowUser {
    return FollowUser(followee)
}

package com.woogie.realworld.domain.followuser.domain

import com.woogie.realworld.domain.user.domain.UserEmail
import com.woogie.realworld.domain.user.domain.Username
import com.woogie.realworld.fixture.createFollowUser
import com.woogie.realworld.fixture.createUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FollowUserTest {

    @Test
    fun `FollowUser 생성 성공`() {
        val followUser = FollowUser(createUser())

        assertThat(followUser.followee).isEqualTo(createUser())
    }

    @Test
    fun `팔로우 성공`() {
        val followUser = createFollowUser()

        val follower1 = createUser(Username("follower1"), UserEmail("follower1@naver.com"))
        val follower2 = createUser(Username("follower2"), UserEmail("follower2@naver.com"))

        followUser.follow(follower1)
        followUser.follow(follower2)

        assertThat(followUser.followers).containsExactly(follower1, follower2)
    }

    @Test
    fun `언팔로우 성공`() {
        val followUser = createFollowUser()

        val follower1 = createUser(Username("follower1"), UserEmail("follower1@naver.com"))
        val follower2 = createUser(Username("follower2"), UserEmail("follower2@naver.com"))

        followUser.follow(follower1)
        followUser.follow(follower2)

        assertThat(followUser.followers).hasSize(2)

        followUser.unfollow(follower2)

        assertThat(followUser.followers).containsExactly(follower2)
    }
}

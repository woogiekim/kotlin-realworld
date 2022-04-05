package com.woogie.realworld.domain.followuser.domain

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.domain.UserEmail
import com.woogie.realworld.domain.user.domain.UserRepository
import com.woogie.realworld.domain.user.domain.Username
import com.woogie.realworld.fixture.createFollowUser
import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.BaseRepositoryTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import javax.persistence.EntityManager

internal class FollowUserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val followUserRepository: FollowUserRepository,
    private val entityManager: EntityManager
) : BaseRepositoryTest() {

    private lateinit var followee: User
    private lateinit var follower: User
    private lateinit var unfollower: User

    @BeforeEach
    fun setUp() {
        followee = userRepository.save(createUser(Username("followee"), UserEmail("followee@naver.com")))
        follower = userRepository.save(createUser(Username("follower"), UserEmail("follower@naver.com")))
        unfollower = userRepository.save(createUser(Username("unfollower"), UserEmail("unfollower@naver.com")))
    }

    @Test
    fun `FollowUser 생성 성공`() {
        val followUser = followUserRepository.save(createFollowUser(followee))

        entityManager.clear()

        val foundFollowUser = followUserRepository.findByIdOrNull(followUser.id)!!

        assertThat(foundFollowUser.followee).isEqualTo(followee)
    }

    @Test
    fun `팔로우 유무 확인`() {
        val followUser = followUserRepository.save(createFollowUser(followee))

        followUser.follow(follower)

        assertThat(followUserRepository.existsByFollower(follower)).isTrue
        assertThat(followUserRepository.existsByFollower(unfollower)).isFalse
    }
}

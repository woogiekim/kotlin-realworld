package com.woogie.realworld.domain.user.service

import com.woogie.realworld.domain.user.domain.UserRepository
import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.fixture.createUserEmail
import com.woogie.realworld.fixture.createUsername
import com.woogie.realworld.support.BaseServiceTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

internal class UserFollowCommandTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userFollowUseCase: UserFollowUseCase
) : BaseServiceTest() {
    @Test
    fun `유저 팔로우 성공`() {
        val followee =
            userRepository.save(createUser(createUsername("followee "), createUserEmail("followee@naver.com")))

        val follower1 =
            userRepository.save(createUser(createUsername("follower1"), createUserEmail("follower1@naver.com")))
        val follower2 =
            userRepository.save(createUser(createUsername("follower2"), createUserEmail("follower2@naver.com")))
        val unFollower =
            userRepository.save(createUser(createUsername("unFollower"), createUserEmail("unFollower@naver.com")))

        userFollowUseCase.follow(followee.username, follower1.id!!)
        userFollowUseCase.follow(followee.username, follower2.id!!)

        val foundFollowee = userRepository.findByIdOrNull(followee.id)!!

        assertThat(foundFollowee.followers).containsExactly(follower1, follower2)
        assertThat(foundFollowee.followers).doesNotContain(unFollower)
    }
}

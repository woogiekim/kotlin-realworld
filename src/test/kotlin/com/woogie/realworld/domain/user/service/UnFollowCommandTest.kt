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

internal class UnFollowCommandTest @Autowired constructor(
    private val unFollowUseCase: UnFollowUseCase,
    private val followUseCase: FollowUseCase,
    private val userRepository: UserRepository
) : BaseServiceTest() {
    @Test
    fun `유저 언팔로우 성공`() {
        val followee =
            userRepository.save(createUser(createUsername("followee "), createUserEmail("followee@naver.com")))

        val unFollower1 =
            userRepository.save(createUser(createUsername("unFollower1"), createUserEmail("follower1@naver.com")))
        val unFollower2 =
            userRepository.save(createUser(createUsername("unFollower2"), createUserEmail("follower2@naver.com")))
        val follower =
            userRepository.save(createUser(createUsername("follower"), createUserEmail("unFollower@naver.com")))

        followUseCase.followOrUnFollow(followee.username, unFollower1.id!!)
        followUseCase.followOrUnFollow(followee.username, unFollower2.id!!)
        followUseCase.followOrUnFollow(followee.username, follower.id!!)

        unFollowUseCase.unFollow(followee.username, unFollower1.id!!)
        unFollowUseCase.unFollow(followee.username, unFollower2.id!!)

        val foundFollowee = userRepository.findByIdOrNull(followee.id)!!

        assertThat(foundFollowee.followers).doesNotContain(unFollower1, unFollower1)
        assertThat(foundFollowee.followers).containsExactly(follower)
    }
}

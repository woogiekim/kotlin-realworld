package com.woogie.realworld.service

import com.woogie.realworld.domain.user.UserRepository
import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.findByIdOrThrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class DefaultUserServiceTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository
) : BaseServiceTest() {

    @Test
    internal fun `사용자 생성 성공`() {
        val user = userService.register(createUser())

        val foundUser = userRepository.findByIdOrThrow(user.id)

        assertThat(foundUser.id).isNotNull
        assertThat(foundUser.createAt).isNotNull
    }
}

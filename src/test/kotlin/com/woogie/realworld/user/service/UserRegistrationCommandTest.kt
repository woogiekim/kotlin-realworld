package com.woogie.realworld.user.service

import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.BaseServiceTest
import com.woogie.realworld.user.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

internal class UserRegistrationCommandTest @Autowired constructor(
    private val userRegistrationCommand: UserRegistrationCommand,
    private val userRepository: UserRepository
) : BaseServiceTest() {

    @Test
    fun `사용자 생성 성공`() {
        val user = userRegistrationCommand.register(createUser())

        val foundUser = userRepository.findByIdOrNull(user.id)!!

        assertThat(foundUser.id).isNotNull
        assertThat(foundUser.createAt).isNotNull
    }
}

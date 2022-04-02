package com.woogie.realworld.user.service

import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.BaseServiceTest
import com.woogie.realworld.support.findByIdOrThrow
import com.woogie.realworld.user.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class DefaultUserServiceTest @Autowired constructor(
    private val userRegister: UserRegisterUseCase,
    private val userRepository: UserRepository
) : BaseServiceTest() {

    @Test
    internal fun `사용자 생성 성공`() {
        val user = userRegister.register(createUser())

        val foundUser = userRepository.findByIdOrThrow(user.id)

        assertThat(foundUser.id).isNotNull
        assertThat(foundUser.createAt).isNotNull
    }
}

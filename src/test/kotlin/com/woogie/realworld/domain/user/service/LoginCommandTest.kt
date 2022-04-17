package com.woogie.realworld.domain.user.service

import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.BaseServiceTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class LoginCommandTest @Autowired constructor(
    private val loginUseCase: LoginUseCase,
    private val userRegistrationUseCase: UserRegistrationUseCase
) : BaseServiceTest() {

    @Test
    fun `로그인 성공`() {
        val user = userRegistrationUseCase.register(createUser())

        val (loginUser, token) = loginUseCase.login(user.email, user.password)

        assertThat(loginUser.id).isEqualTo(user.id)
        assertThat(token).isNotNull
    }
}

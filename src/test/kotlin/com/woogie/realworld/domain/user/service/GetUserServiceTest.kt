package com.woogie.realworld.domain.user.service

import com.woogie.realworld.domain.user.service.GetUserQuery
import com.woogie.realworld.domain.user.service.UserRegistrationUseCase
import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.BaseServiceTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class GetUserServiceTest @Autowired constructor(
    private val getUserQuery: GetUserQuery,
    private val userRegistrationUseCase: UserRegistrationUseCase
) : BaseServiceTest() {

    @Test
    fun `사용자 조회`() {
        val user = userRegistrationUseCase.register(createUser())

        val foundUser = getUserQuery.getUser(user.id!!)

        assertThat(foundUser).isEqualTo(user)
    }
}

package com.woogie.realworld.domain.user

import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.findByIdOrThrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository
) {
    @Test
    fun `사용자 생성 성공`() {
        val user = userRepository.save(createUser())

        val foundUser = userRepository.findByIdOrThrow(user.id)

        assertThat(user.id).isNotNull
        assertThat(user).isEqualTo(foundUser)
    }
}

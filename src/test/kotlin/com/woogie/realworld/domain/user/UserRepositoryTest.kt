package com.woogie.realworld.domain.user

import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.findByIdOrThrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager

@DataJpaTest
internal class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val entityManager: EntityManager
) {
    @Test
    fun `사용자 생성 성공`() {
        var user = createUser()

        user = userRepository.save(user)

        assertThat(user.id).isNotNull

        entityManager.clear()

        val foundUser = userRepository.findByIdOrThrow(user.id)

        assertThat(foundUser.email).isEqualTo(user.email)
        assertThat(foundUser.name).isEqualTo(user.name)
        assertThat(foundUser.password).isEqualTo(user.password)
    }
}

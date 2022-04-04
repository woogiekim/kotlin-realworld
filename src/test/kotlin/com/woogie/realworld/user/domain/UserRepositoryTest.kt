package com.woogie.realworld.user.domain

import com.woogie.realworld.fixture.createUser
import com.woogie.realworld.support.BaseRepositoryTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import javax.persistence.EntityManager

internal class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val entityManager: EntityManager
) : BaseRepositoryTest() {
    @Test
    fun `User 생성 성공`() {
        var user = createUser()

        user = userRepository.save(user)

        assertThat(user.id).isNotNull

        entityManager.clear()

        val foundUser = userRepository.findByIdOrNull(user.id)!!

        assertThat(foundUser.email).isEqualTo(user.email)
        assertThat(foundUser.username).isEqualTo(user.username)
        assertThat(foundUser.password).isEqualTo(user.password)
    }
}

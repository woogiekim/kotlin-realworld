package com.woogie.realworld.domain.user.service

import com.woogie.realworld.domain.user.domain.*
import com.woogie.realworld.fixture.*
import com.woogie.realworld.support.BaseServiceTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

internal class UserUpdateCommandTest @Autowired constructor(
    private val userUpdateUseCase: UserUpdateUseCase,
    private val userRegistrationUseCase: UserRegistrationUseCase,
    private val passwordValidator: PasswordValidator,
    private val userRepository: UserRepository
) : BaseServiceTest() {
    @Test
    fun `사용자 수정 성공`() {
        val user = userRegistrationUseCase.register(createUser())

        assertThat(user.username).isEqualTo(createUsername())
        assertThat(user.email).isEqualTo(createUserEmail())
        assertThat(passwordValidator.validate(createUserPassword(), user)).isTrue
        assertThat(user.image).isEqualTo(createUserImage())
        assertThat(user.bio).isEqualTo(createUserBio())

        userUpdateUseCase.update(
            user.id!!, Username("Taewook Kim"), UserEmail("mdir2@naver.com"),
            UserPassword("7654321"), UserImage("changed.png"), UserBio("복행")
        )

        val foundUser = userRepository.findByIdOrNull(user.id)!!

        assertThat(foundUser.username).isEqualTo(Username("Taewook Kim"))
        assertThat(foundUser.email).isEqualTo(UserEmail("mdir2@naver.com"))
        assertThat(foundUser.image).isEqualTo(UserImage("changed.png"))
        assertThat(foundUser.bio).isEqualTo(UserBio("복행"))
    }
}

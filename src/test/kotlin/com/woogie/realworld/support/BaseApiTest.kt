package com.woogie.realworld.support

import com.woogie.realworld.domain.user.domain.*
import com.woogie.realworld.domain.user.service.LoginCommand
import com.woogie.realworld.domain.user.service.UserRegistrationUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
abstract class BaseApiTest {
    @Autowired
    protected lateinit var userRegistrationUseCase: UserRegistrationUseCase

    @Autowired
    private lateinit var userLoginCommand: LoginCommand

    protected fun prepareLogInUser(
        email: UserEmail = UserEmail("woogie@gmail.com"),
        password: UserPassword = UserPassword("1234567"),
        username: Username = Username("woogie"),
        bio: UserBio = UserBio("good"),
        image: UserImage = UserImage("woogie.png")
    ): Pair<User, String> {
        var user = User.create(email, password, username, bio, image)

        user = userRegistrationUseCase.register(user)

        return userLoginCommand.login(user.email, user.password)
    }
}

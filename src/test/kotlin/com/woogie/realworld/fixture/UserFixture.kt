package com.woogie.realworld.fixture

import com.woogie.realworld.user.domain.User
import com.woogie.realworld.user.domain.UserEmail
import com.woogie.realworld.user.domain.UserPassword
import com.woogie.realworld.user.domain.Username

fun createUser(
    name: Username = createUsername(),
    email: UserEmail = createUserEmail(),
    password: UserPassword = createUserPassword(),
): User {
    return User(name, email, password)
}

fun createUsername(
    name: String = "김태욱"
): Username {
    return Username(name)
}

fun createUserEmail(
    email: String = "wook@gmail.com"
): UserEmail {
    return UserEmail(email)
}

fun createUserPassword(
    password: String = "1234567"
): UserPassword {
    return UserPassword(password)
}

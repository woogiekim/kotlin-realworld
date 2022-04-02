package com.woogie.realworld.fixture

import com.woogie.realworld.user.domain.User
import com.woogie.realworld.user.domain.UserEmail
import com.woogie.realworld.user.domain.UserPassword
import com.woogie.realworld.user.domain.Username

fun createUser(
    email: UserEmail = createUserEmail(),
    name: Username = createUsername(),
    password: UserPassword = createUserPassword(),
): User {
    return User(email, name, password)
}

fun createUserEmail(
    email: String = "wook@gmail.com"
): UserEmail {
    return UserEmail(email)
}

fun createUsername(
    name: String = "김태욱"
): Username {
    return Username(name)
}

fun createUserPassword(
    password: String = "1234567"
): UserPassword {
    return UserPassword(password)
}

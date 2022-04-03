package com.woogie.realworld.fixture

import com.woogie.realworld.user.domain.*

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

fun createUserImage(
    image: String = "image.png"
): UserImage {
    return UserImage(image)
}

fun createUserBio(
    bio: String = "행복"
): UserBio {
    return UserBio(bio)
}

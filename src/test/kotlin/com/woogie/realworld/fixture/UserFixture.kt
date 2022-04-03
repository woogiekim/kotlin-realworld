package com.woogie.realworld.fixture

import com.woogie.realworld.user.domain.*

fun createUser(
    name: Username = createUsername(),
    email: UserEmail = createUserEmail(),
    password: UserPassword = createUserPassword(),
): User {
    return User.create(email, password, name)
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

fun createProfile(
    username: Username = createUsername(),
    bio: UserBio = createUserBio(),
    image: UserImage = createUserImage()
): Profile {
    return Profile(username, bio, image)
}

fun createUsername(
    name: String = "김태욱"
): Username {
    return Username(name)
}

fun createUserBio(
    bio: String = "행복"
): UserBio {
    return UserBio(bio)
}

fun createUserImage(
    image: String = "image.png"
): UserImage {
    return UserImage(image)
}

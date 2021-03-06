package com.woogie.realworld.domain.user.domain

import com.woogie.realworld.fixture.createUser
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

internal class UserTest {
    @Test
    fun `User 생성 성공`() {
        val email = UserEmail("wook@gmail.com")
        val password = UserPassword("1234567")
        val username = Username("김태욱")

        val user = User.create(email, password, username)

        assertThat(user.username).isEqualTo(username)
        assertThat(user.email).isEqualTo(email)
        assertThat(user.password).isEqualTo(password)
        assertThat(user.bio).isNull()
        assertThat(user.image).isNull()
        assertThat(user.createAt).isNotNull
    }

    @Test
    fun `User 수정 성공`() {
        val user = createUser()

        val username = Username("김태욱")

        assertThat(user.username).isEqualTo(username)
        assertThat(user.email).isEqualTo(UserEmail("wook@gmail.com"))
        assertThat(user.password).isEqualTo(UserPassword("1234567"))

        val updatedUsername = Username("Taewook Kim")
        val updatedImage = UserImage("image.png")
        val updatedBio = UserBio("행복")

        user.update(
            UserEmail("mdir2@naver.com"), UserPassword("7654321"), updatedUsername,
            updatedBio, updatedImage
        )

        assertThat(user.username).isEqualTo(updatedUsername)
        assertThat(user.email).isEqualTo(UserEmail("mdir2@naver.com"))
        assertThat(user.password).isEqualTo(UserPassword("7654321"))
        assertThat(user.bio).isEqualTo(updatedBio)
        assertThat(user.image).isEqualTo(updatedImage)
    }

    @Test
    fun `팔로우 성공`() {
        val followee = createUser(Username("followee"), UserEmail("followee@naver.com")).apply { id = 1 }

        val follower1 = createUser(Username("follower1"), UserEmail("follower1@naver.com")).apply { id = 2 }
        val follower2 = createUser(Username("follower2"), UserEmail("follower2@naver.com")).apply { id = 3 }

        followee.follow(follower1)
        followee.follow(follower2)

        assertThat(followee.followers).containsExactly(follower1, follower2)
    }

    @Test
    fun `본인을 팔로우 하려고 하면 실패`() {
        val followee = createUser(Username("followee"), UserEmail("followee@naver.com")).apply { id = 1 }

        assertThatIllegalArgumentException().isThrownBy {
            followee.follow(followee)
        }
    }

    @Test
    fun `언팔로우 성공`() {
        val followee = createUser(Username("followee"), UserEmail("followee@naver.com")).apply { id = 1 }

        val follower = createUser(Username("follower"), UserEmail("follower@naver.com")).apply { id = 2 }
        val unFollower = createUser(Username("unFollower"), UserEmail("unFollower@naver.com")).apply { id = 3 }

        followee.follow(follower)
        followee.follow(unFollower)

        followee.unfollow(unFollower)

        assertThat(followee.followers).containsExactly(follower)
        assertThat(followee.followers).doesNotContain(unFollower)
    }

    @Test
    fun `본인을 언팔로우 하려고 하면 실패`() {
        val followee = createUser(Username("followee"), UserEmail("followee@naver.com")).apply { id = 1 }

        assertThatIllegalArgumentException().isThrownBy {
            followee.unfollow(followee)
        }
    }
}

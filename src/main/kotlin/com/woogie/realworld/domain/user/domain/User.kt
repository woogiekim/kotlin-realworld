package com.woogie.realworld.domain.user.domain

import com.woogie.realworld.domain.user.service.PasswordGenerator
import com.woogie.realworld.support.BaseAggregateRoot
import java.time.OffsetDateTime
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.ManyToMany
import javax.persistence.OneToOne

@Entity
class User(
    /** 이메일 **/
    var email: UserEmail,

    /** 비밀번호 **/
    var password: UserPassword,

    /** 사용자 프로필 **/
    @OneToOne(cascade = [ALL], fetch = LAZY)
    val profile: Profile,

    /** 팔로워 **/
    @ManyToMany(cascade = [ALL], fetch = LAZY)
    val followers: MutableList<User> = mutableListOf(),

    /** 등록 일시 **/
    val createAt: OffsetDateTime = OffsetDateTime.now()
) : BaseAggregateRoot<User>() {
    val username: Username
        get() = this.profile.username

    val bio: UserBio?
        get() = this.profile.bio

    val image: UserImage?
        get() = this.profile.image

    fun update(email: UserEmail, password: UserPassword, username: Username, bio: UserBio?, image: UserImage?) {
        this.email = email
        this.password = password

        this.profile.update(username, bio, image)
    }

    fun follow(follower: User) {
        require(this != follower) { "본인을 팔로우 할 수 없음" }

        this.followers.add(follower)
    }

    fun unfollow(follower: User) {
        require(this != follower) { "본인을 언팔로우 할 수 없음" }

        this.followers.remove(follower)
    }

    fun following(follower: User): Boolean {
        return this.followers.contains(follower)
    }

    fun encodePassword(generator: PasswordGenerator) {
        this.password = generator.generate(this.password)
    }

    companion object {
        fun create(
            email: UserEmail, password: UserPassword, username: Username, bio: UserBio? = null, image: UserImage? = null
        ): User {
            val profile = Profile(username, bio, image)

            return User(email, password, profile)
        }
    }
}

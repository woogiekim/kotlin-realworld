package com.woogie.realworld.user.domain

import com.woogie.realworld.support.BaseAggregateRoot
import java.time.OffsetDateTime
import javax.persistence.Entity

@Entity
class User(
    /* 이름 */
    var name: Username,

    /* 이메일 */
    var email: UserEmail,

    /* 비밀번호 */
    var password: UserPassword,

    /* 이미지 */
    var image: UserImage? = null,

    /* 바이오 */
    var bio: UserBio? = null,

    /* 등록 일시 */
    val createAt: OffsetDateTime = OffsetDateTime.now()
) : BaseAggregateRoot<User>() {
    fun update(name: Username, email: UserEmail, password: UserPassword, image: UserImage?, bio: UserBio?) {
        this.name = name
        this.email = email
        this.password = password
        this.image = image
        this.bio = bio
    }
}

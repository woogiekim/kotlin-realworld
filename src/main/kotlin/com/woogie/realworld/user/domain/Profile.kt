package com.woogie.realworld.user.domain

import com.woogie.realworld.support.BaseAggregate
import javax.persistence.Entity

@Entity
class Profile(
    /** 사용자 이름 **/
    var username: Username,

    /** 바이오 **/
    var bio: UserBio?,

    /** 이미지 **/
    var image: UserImage?
) : BaseAggregate() {
    fun update(username: Username, bio: UserBio?, image: UserImage?) {
        this.username = username
        this.bio = bio
        this.image = image
    }
}

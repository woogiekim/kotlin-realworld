package com.woogie.realworld.domain.followuser.domain

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.support.BaseAggregateRoot
import javax.persistence.CascadeType.PERSIST
import javax.persistence.CascadeType.REMOVE
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class FollowUser(
    @ManyToOne(fetch = FetchType.LAZY)
    val followee: User,

    @OneToMany(cascade = [PERSIST, REMOVE], fetch = FetchType.LAZY)
    val followers: MutableList<User> = mutableListOf()
) : BaseAggregateRoot<FollowUser>() {
    fun follow(follower: User) {
        this.followers.add(follower)
    }

    fun unfollow(follower: User) {
        this.followers.remove(follower)
    }
}

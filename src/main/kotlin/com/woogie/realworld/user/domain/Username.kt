package com.woogie.realworld.user.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Username(
    @Column(length = 100)
    val username: String
) {
    override fun toString(): String {
        return username
    }
}

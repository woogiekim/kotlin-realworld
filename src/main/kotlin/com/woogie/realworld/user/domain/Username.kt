package com.woogie.realworld.user.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Username(
    @Column(name = "username", length = 100)
    val value: String
)

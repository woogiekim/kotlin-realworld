package com.woogie.realworld.user.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Username(
    @Column
    val name: String
)

package com.woogie.realworld.domain.user

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Username(
    @Column
    val name: String
)

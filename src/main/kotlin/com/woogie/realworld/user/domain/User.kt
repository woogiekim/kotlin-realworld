package com.woogie.realworld.user.domain

import com.woogie.realworld.support.BaseAggregateRoot
import java.time.OffsetDateTime
import javax.persistence.Entity

@Entity
class User(
    /* 이름 */
    val name: Username,

    /* 이메일 */
    val email: UserEmail,

    /* 비밀번호 */
    val password: UserPassword,

    /* 등록 일시 */
    val createAt: OffsetDateTime = OffsetDateTime.now()
) : BaseAggregateRoot<User>()

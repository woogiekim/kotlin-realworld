package com.woogie.realworld.security

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.domain.UserPassword
import com.woogie.realworld.domain.user.service.PasswordValidator
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DefaultPasswordValidator(
    private val passwordEncoder: PasswordEncoder
) : PasswordValidator {
    override fun validate(rawPassword: UserPassword, user: User): Boolean {
        return passwordEncoder.matches(rawPassword.toString(), user.password.toString())
    }
}

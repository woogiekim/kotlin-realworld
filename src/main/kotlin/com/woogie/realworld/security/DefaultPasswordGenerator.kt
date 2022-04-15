package com.woogie.realworld.security

import com.woogie.realworld.domain.user.domain.UserPassword
import com.woogie.realworld.domain.user.service.PasswordGenerator
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DefaultPasswordGenerator(
    private val passwordEncoder: PasswordEncoder
) : PasswordGenerator {
    override fun generate(password: UserPassword): UserPassword {
        return UserPassword(passwordEncoder.encode(password.toString()))
    }
}

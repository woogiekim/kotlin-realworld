package com.woogie.realworld.security

import com.woogie.realworld.domain.user.domain.UserEmail
import com.woogie.realworld.domain.user.service.GetUserQuery
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CustomUserDetailsService(
    private val getUserQuery: GetUserQuery
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = getUserQuery.getUser(UserEmail(email))

        return SecurityUser(user)
    }
}

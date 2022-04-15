package com.woogie.realworld.domain.user.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: UserEmail): User?

    @Query(
        """
        select u
        from User u
        join fetch u.profile p
        where p.username = ?1
    """
    )
    fun findByUsername(username: Username): User?
}

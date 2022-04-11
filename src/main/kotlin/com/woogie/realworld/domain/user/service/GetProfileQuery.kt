package com.woogie.realworld.domain.user.service

import com.woogie.realworld.domain.user.domain.Profile
import com.woogie.realworld.domain.user.domain.UserRepository
import com.woogie.realworld.domain.user.domain.Username
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface GetProfileQuery {
    /**
     * 사용자 프로필 조회
     */
    fun getProfile(username: Username, currentUserId: Long?): Pair<Profile, Boolean>
}

@Service
@Transactional
class GetProfileService(
    private val userRepository: UserRepository
) : GetProfileQuery {
    override fun getProfile(username: Username, currentUserId: Long?): Pair<Profile, Boolean> {
        val user = userRepository.findByUsername(username)!!

        val currentUser = userRepository.findByIdOrNull(currentUserId)
        val following = currentUser?.let { user.following(it) } ?: false

        return Pair(user.profile, following)
    }
}

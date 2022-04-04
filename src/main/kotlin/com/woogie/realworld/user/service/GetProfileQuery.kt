package com.woogie.realworld.user.service

import com.woogie.realworld.user.domain.Profile
import com.woogie.realworld.user.domain.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface GetProfileQuery {
    /**
     * 사용자 프로필 조회
     */
    fun getProfile(id: Long): Profile
}

@Service
@Transactional
class GetProfileService(
    private val userRepository: UserRepository
) : GetProfileQuery {
    override fun getProfile(id: Long): Profile {
        val user = userRepository.findByIdOrNull(id)!!

        return user.profile
    }
}

package com.dollee.sitly.service

import com.dollee.sitly.dto.UserRequest
import com.dollee.sitly.dto.UserResponse
import com.dollee.sitly.dto.toResponse
import com.dollee.sitly.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {
    fun getMyInfo(loginId: String): UserResponse {
        val user = userRepository.findByLoginId(loginId)
        val found = user.id?.let { userRepository.findById(it) }
            ?: throw IllegalArgumentException("로그인 아이디의 USER ID가 없습니다 : $loginId")
        return found.toResponse()
    }

    fun register(userRequest: UserRequest): UserResponse {
        TODO()
    }
}
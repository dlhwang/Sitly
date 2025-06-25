package com.dollee.sitly.service

import com.dollee.sitly.dto.UserResponse
import com.dollee.sitly.dto.toResponse
import com.dollee.sitly.user.service.UserDomainService
import org.springframework.stereotype.Service

@Service
class UserService(
    val userDomainService: UserDomainService
) {
    fun getMyInfo(loginId: String): UserResponse {
        return userDomainService.getMyInfo(loginId).toResponse()
    }

    fun register(command: RegisterUserCommand): UserResponse {
        return userDomainService.register(command).toResponse()
    }

    fun modify(loginId: String, command: RegisterUserCommand): UserResponse {
        return userDomainService.modify(loginId, command).toResponse()
    }

    fun modify(loginId: String, command: UserInput): UserResponse {
        return userDomainService.modify(loginId, command).toResponse()
    }

    fun modify(loginId: String, command: AccountInput): UserResponse {
        return userDomainService.modify(loginId, command).toResponse()
    }

    fun modify(loginId: String, command: SitterInput): UserResponse {
        return userDomainService.modify(loginId, command).toResponse()
    }

    fun modify(loginId: String, command: MomInput): UserResponse {
        return userDomainService.modify(loginId, command).toResponse()
    }
}
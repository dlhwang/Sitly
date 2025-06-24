package com.dollee.sitly.service

import com.dollee.sitly.config.security.JwtAuthenticationProvider
import com.dollee.sitly.user.domain.model.User
import com.dollee.sitly.user.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val jwtProvider: JwtAuthenticationProvider

) {
    fun findByLoginId(loginId: String): User {
        return userRepository.findByLoginId(loginId)
    }

    suspend fun login(username: String, password: String): String = withContext(Dispatchers.IO) {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(username, password)
        )

        val user = authentication.principal as UserDetails
        return@withContext jwtProvider.generateJwtToken(user)
    }
}
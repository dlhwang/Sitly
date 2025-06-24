package com.dollee.sitly.config.security

import com.dollee.sitly.user.domain.model.LoginId
import com.dollee.sitly.user.domain.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByLoginId(username)
        return AuthUser.newInstance(
            LoginId(username),
            user.password.value,
            listOf(SimpleGrantedAuthority("ROLE_USER"))
        )
    }
}

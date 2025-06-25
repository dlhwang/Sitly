package com.dollee.sitly.config.security

import com.dollee.sitly.user.domain.model.LoginId
import io.jsonwebtoken.Claims
import lombok.AccessLevel
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serial

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class AuthUser(
    private val username: LoginId,
    private val password: String,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return authorities
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String {
        return username.value
    }

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    companion object {
        @Serial
        private const val serialVersionUID = 5894115671208145002L

        fun newInstance(
            username: LoginId,
            password: String,
            authorities: Collection<GrantedAuthority>
        ): AuthUser {
            return AuthUser(username, password, authorities.toMutableList())
        }

        fun to(claims: Claims): AuthUser {
            val userId = claims.subject ?: throw IllegalArgumentException("subject 없음")
            val rolesClaim = claims["roles"]
                ?: throw IllegalArgumentException("roles claim 없음")

            val rolesList = (rolesClaim as? List<*>)
                ?.mapNotNull { it as? Map<*, *> }
                ?: throw IllegalArgumentException("roles 클레임 형식이 잘못되었습니다.")

            val authorities = rolesList.mapNotNull { roleMap ->
                val authority = roleMap["authority"]
                if (authority is String && authority.isNotBlank()) {
                    SimpleGrantedAuthority(authority)
                } else null
            }

            return newInstance(LoginId(userId), "PROTECTED", authorities)
        }
    }
}

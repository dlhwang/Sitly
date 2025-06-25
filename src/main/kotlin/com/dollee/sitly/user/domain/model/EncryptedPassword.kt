package com.dollee.sitly.user.domain.model

import com.dollee.sitly.user.domain.service.PasswordPolicy
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.security.crypto.password.PasswordEncoder

@JsonIgnoreProperties("value")
@JvmInline
value class EncryptedPassword(val value: String) {
    companion object {
        fun fromRaw(rawPassword: String, encoder: PasswordEncoder): EncryptedPassword {
            PasswordPolicy.validate(rawPassword)
            return EncryptedPassword(encoder.encode(rawPassword))
        }

        fun fromEncrypted(encrypted: String): EncryptedPassword {
            require(encrypted.startsWith("\$2")) { "BCrypt 해시가 아닙니다." }
            return EncryptedPassword(encrypted)
        }
    }

    init {
        require(value.startsWith("\$2")) { "BCrypt 해시가 아닙니다." } // 예시로 bcrypt 검증
    }

    fun matches(rawPassword: String, encoder: PasswordEncoder): Boolean {
        return encoder.matches(rawPassword, value)
    }

    override fun toString(): String = "PROTECTED" // 노출 방지
}
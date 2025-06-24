package com.dollee.sitly.user.domain.model

import org.springframework.security.crypto.password.PasswordEncoder

@JvmInline
value class EncryptedPassword(val value: String) {
    init {
        require(value.startsWith("\$2")) { "BCrypt 해시가 아닙니다." } // 예시로 bcrypt 검증
    }

    fun matches(rawPassword: String, encoder: PasswordEncoder): Boolean {
        return encoder.matches(rawPassword, value)
    }

    override fun toString(): String = "PROTECTED" // 노출 방지
}
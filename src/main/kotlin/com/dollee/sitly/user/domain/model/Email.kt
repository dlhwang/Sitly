package com.dollee.sitly.user.domain.model

@JvmInline
value class Email(val value: String) {

    init {
        require(isValidEmail(value)) { "이메일 형식이 올바르지 않습니다: $value" }
    }

    companion object {
        private val EMAIL_REGEX = Regex(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$"
        )

        fun isValidEmail(email: String): Boolean {
            return EMAIL_REGEX.matches(email)
        }
    }
}
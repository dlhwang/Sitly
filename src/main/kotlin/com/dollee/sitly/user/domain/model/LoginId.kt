package com.dollee.sitly.user.domain.model

@JvmInline
value class LoginId(val value: String) {
    init {
        require(value.isNotBlank()) { "로그인 ID는 필수입니다." }
    }

    override fun toString() = value
}
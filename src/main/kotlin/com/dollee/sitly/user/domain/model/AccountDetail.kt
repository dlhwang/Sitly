package com.dollee.sitly.user.domain.model

data class AccountDetail(
    val loginId: LoginId,
    val password: EncryptedPassword,
    val email: Email
)

package com.dollee.sitly.user.infra.entity

import com.dollee.sitly.user.domain.model.Email
import com.dollee.sitly.user.domain.model.EncryptedPassword
import com.dollee.sitly.user.domain.model.LoginId
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class AccountDetail(
    @Column(name = "login_id", nullable = false, unique = true)
    val loginId: LoginId,
    @Column(name = "password", nullable = false)
    val password: EncryptedPassword,
    @Column(name = "email", nullable = false, unique = true)
    val email: Email
)

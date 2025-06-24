package com.dollee.sitly.user.infra.entity

import com.dollee.sitly.global.utills.Ulid
import com.dollee.sitly.user.domain.model.EncryptedPassword
import com.dollee.sitly.user.domain.model.LoginId
import com.dollee.sitly.user.domain.model.enumtype.Gender
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "users")
class UserEntity(
    @Id @Ulid
    val id: String? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val birthdate: LocalDate,

    @Column(nullable = false)
    val gender: Gender,

    @Column(name = "login_id", nullable = false, unique = true)
    val loginId: LoginId,

    @Column(name = "password", nullable = false)
    val password: EncryptedPassword

) {
}
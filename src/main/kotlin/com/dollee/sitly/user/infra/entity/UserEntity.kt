package com.dollee.sitly.user.infra.entity

import com.dollee.sitly.global.utills.Ulid
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Id @Ulid
    val id: String? = null,

    @get:Embedded
    val userDetail: UserDetail,

    @get:Embedded
    val accountDetail: AccountDetail,
) {
}
package com.dollee.sitly.user.infra.entity

import com.dollee.sitly.global.utills.Ulid
import jakarta.persistence.*

@Entity
@Table(name = "children")
class ChildEntity(
    @Id @Ulid
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String? = null,

    @Embedded private var userDetail: UserDetail,
) {
    fun modify(newDetail: UserDetail) {
        this.userDetail = newDetail
    }

    fun getUserDetail(): UserDetail = userDetail
}

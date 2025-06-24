package com.dollee.sitly.user.infra.entity

import jakarta.persistence.*

@Entity
@Table(name = "users_sitter")
class SitterEntity(
    @Id
    val id: String? = null,
    @MapsId @OneToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity
) {
}
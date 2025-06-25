package com.dollee.sitly.user.infra.entity

import jakarta.persistence.*

@Entity
@Table(name = "users_sitter")
class SitterEntity(
    @Id
    val id: String? = null,
    @MapsId @OneToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @Column(columnDefinition = "TEXT", nullable = false)
    val introduction: String,
    @Column(name = "carable_age_from", nullable = false)
    val carableAgeFrom: Int,
    @Column(name = "carable_age_to", nullable = false)
    val carableAgeTO: Int
) {
}
package com.dollee.sitly.user.infra.entity

import jakarta.persistence.*

@Entity
@Table(name = "users_mom")
class MomEntity(
    @Id
    val id: String? = null,
    @MapsId @OneToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,
    @Column(name = "child_note", columnDefinition = "TEXT", nullable = false)
    val childNote: String,
    @Column(name = "request_message", columnDefinition = "TEXT", nullable = false)
    val requestMessage: String,
) {
}
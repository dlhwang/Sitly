package com.dollee.sitly.user.infra.entity

import jakarta.persistence.*

@Entity
@Table(name = "users_mom")
class MomEntity(
    @Id
    val id: String? = null,
    @MapsId @OneToOne
    @JoinColumn(name = "user_id")
    private var user: UserEntity,
    @Column(name = "child_note", columnDefinition = "TEXT", nullable = false)
    private var childNote: String,
    @Column(name = "request_message", columnDefinition = "TEXT", nullable = false)
    private var requestMessage: String,
) {
    fun modify(childNote: String, requestMessage: String) {
        this.childNote = childNote
        this.requestMessage = requestMessage
    }

    fun getChildNote(): String = childNote
    fun getRequestMessage(): String = requestMessage
}
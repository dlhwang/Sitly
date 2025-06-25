package com.dollee.sitly.user.infra.entity

import jakarta.persistence.*

@Entity
@Table(name = "users_sitter")
class SitterEntity(
    @Id
    @MapsId @OneToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @Column(columnDefinition = "TEXT", nullable = false)
    private var introduction: String,
    @Column(name = "carable_age_from", nullable = false)
    private var carableAgeFrom: Int,
    @Column(name = "carable_age_to", nullable = false)
    private var carableAgeTO: Int
) {
    fun modify(introduction: String, carableAgeFrom: Int, carableAgeTO: Int) {
        this.introduction = introduction
        this.carableAgeFrom = carableAgeFrom
        this.carableAgeTO = carableAgeTO
    }

    fun getIntroduction(): String = introduction
    fun getCarableAgeFrom(): Int = carableAgeFrom
    fun getCarableAgeTo(): Int = carableAgeTO
    fun getId(): String = user.id!!
}
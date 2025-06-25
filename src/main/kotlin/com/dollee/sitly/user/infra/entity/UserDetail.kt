package com.dollee.sitly.user.infra.entity

import com.dollee.sitly.user.domain.model.enumtype.Gender
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.time.LocalDate

@Embeddable
data class UserDetail(
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "birth_date", nullable = false)
    val birthdate: LocalDate,
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    val gender: Gender,
)

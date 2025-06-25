package com.dollee.sitly.user.domain.model

import com.dollee.sitly.user.domain.model.enumtype.Gender
import java.time.LocalDate

data class UserDetail(
    val name: String,
    val birthdate: LocalDate,
    val gender: Gender,
)

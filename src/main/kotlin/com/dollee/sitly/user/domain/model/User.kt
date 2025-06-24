package com.dollee.sitly.user.domain.model

import com.dollee.sitly.user.domain.model.enumtype.Gender
import java.time.LocalDate

class User(
    val id: String? = null,
    val name: String,
    val birthdate: LocalDate,
    val gender: Gender,
    val loginId: LoginId,
    val password: EncryptedPassword
) {

}
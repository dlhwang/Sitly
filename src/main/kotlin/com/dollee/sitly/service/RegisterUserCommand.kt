package com.dollee.sitly.service

import com.dollee.sitly.user.domain.model.enumtype.Gender
import java.time.LocalDate

data class RegisterUserCommand(
    val user: UserInput,
    val account: AccountInput,
    val sitter: SitterInput? = null,
    val mom: MomInput? = null
)

data class UserInput(
    val name: String,
    val birthdate: LocalDate,
    val gender: Gender,
)

data class AccountInput(
    val loginId: String,
    val password: String,
    val email: String
)

data class SitterInput(
    val introduction: String,
    val carableAgeFrom: Int,
    val carableAgeTo: Int
)

data class MomInput(
    val child: List<ChildInput>,
    val requestMessage: String
)

data class ChildInput(
    val detail: UserInput,
)
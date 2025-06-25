package com.dollee.sitly.dto

import com.dollee.sitly.service.*


fun UserRequest.toCommand(): RegisterUserCommand =
    RegisterUserCommand(
        user = UserInput(
            name = this.userDetail.name,
            birthdate = this.userDetail.birthdate,
            gender = this.userDetail.gender
        ),
        account = AccountInput(
            loginId = this.accountDetail.loginId,
            password = this.accountDetail.password,
            email = this.accountDetail.email
        ),
        sitter = this.sitterDetail?.let {
            SitterInput(
                introduction = it.introduction,
                carableAgeFrom = it.carableAgeFrom,
                carableAgeTo = it.carableAgeTo
            )
        },
        mom = this.momDetail?.let {
            MomInput(
                child = it.child.map { c ->
                    ChildInput(
                        detail = UserInput(
                            name = c.detail.name,
                            birthdate = c.detail.birthdate,
                            gender = c.detail.gender
                        )
                    )
                },
                requestMessage = it.requestMessage
            )
        }
    )

fun UserSave.toCommand(): UserInput = UserInput(
    name = this.name,
    birthdate = this.birthdate,
    gender = this.gender
)

fun AccountSave.toCommand(): AccountInput = AccountInput(
    loginId = this.loginId,
    password = this.password,
    email = this.email
)

fun SitterSave.toCommand(): SitterInput = SitterInput(
    introduction = this.introduction,
    carableAgeFrom = this.carableAgeFrom,
    carableAgeTo = this.carableAgeTo
)

fun MomSave.toCommand(): MomInput = MomInput(
    child = this.child.map { c ->
        ChildInput(
            detail = UserInput(
                name = c.detail.name,
                birthdate = c.detail.birthdate,
                gender = c.detail.gender
            )
        )
    },
    requestMessage = this.requestMessage
)

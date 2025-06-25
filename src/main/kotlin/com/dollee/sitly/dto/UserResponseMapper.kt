package com.dollee.sitly.dto

import com.dollee.sitly.user.domain.model.User

fun User.toResponse(): UserResponse = UserResponse(
    id = this.id ?: throw IllegalStateException("User ID가 없습니다"),
    userDetail = UserDetailVO(
        name = userDetail.name,
        birthdate = userDetail.birthdate,
        gender = userDetail.gender
    ),
    accountDetail = AccountDetailVO(
        loginId = accountDetail.loginId,
        password = accountDetail.password,
        email = accountDetail.email
    ),
    sitter = sitter?.let {
        SitterVO(it.introduction, it.carableAgeFrom, it.carableAgeTO)
    },
    mom = mom?.let {
        MomVO(it.getChildren().map { c ->
            ChildVO(
                detail = UserDetailVO(
                    name = c.userDetail.name,
                    birthdate = c.userDetail.birthdate,
                    gender = c.userDetail.gender
                )
            )
        }, it.requestMessage)
    }
)
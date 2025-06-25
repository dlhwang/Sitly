package com.dollee.sitly.dto

import com.dollee.sitly.user.domain.model.Email
import com.dollee.sitly.user.domain.model.EncryptedPassword
import com.dollee.sitly.user.domain.model.LoginId
import com.dollee.sitly.user.domain.model.enumtype.Gender
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "회원")
data class UserResponse(
    @Schema(description = "회원 번호") val id: String,
    @Schema(description = "기본 정보") val userDetail: UserDetailVO,
    @Schema(description = "계정 정보") val accountDetail: AccountDetailVO,
    @Schema(description = "시터 정보") val sitter: SitterVO? = null,
    @Schema(description = "맘 회원 정보") val mom: MomVO? = null
)

@Schema(description = "회원 기본 정보")
data class UserDetailVO(
    @Schema(description = "성함") val name: String,
    @Schema(description = "생일") val birthdate: LocalDate,
    @Schema(description = "성별") val gender: Gender
)

@Schema(description = "계정 정보")
data class AccountDetailVO(
    @Schema(description = "아이디") val loginId: LoginId,
    @Schema(description = "비밀번호") val password: EncryptedPassword,
    @Schema(description = "이메일") val email: Email
)

@Schema(description = "시터 정보")
data class SitterVO(
    @Schema(description = "자기소개") val introduction: String,
    @Schema(description = "케어 가능 연령 From") val carableAgeFrom: Int,
    @Schema(description = "케어 가능 연령 To") val carableAgeTO: Int
)

@Schema(description = "맘 회원 정보")
data class MomVO(
    @Schema(description = "아이 정보") val child: List<ChildVO>,
    @Schema(description = "신청 내용") val requestMessage: String
)

@Schema(description = "맘 자녀 회원 정보")
data class ChildVO(
    @Schema(description = "아이 정보") val detail: UserDetailVO,
)
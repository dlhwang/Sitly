package com.dollee.sitly.dto

import com.dollee.sitly.user.domain.model.enumtype.Gender
import com.dollee.sitly.user.domain.service.PasswordPolicy.PASSWORD_LENGTH
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.*
import java.time.LocalDate

@Schema(description = "회원 등록 객체")
data class UserRequest(
    @Schema(description = "기본 정보")
    @Valid
    val userDetail: UserSave,

    @Schema(description = "계정 정보")
    @Valid
    val accountDetail: AccountSave,

    @Schema(description = "시터 정보")
    @Valid
    val sitterDetail: SitterSave?,

    @Schema(description = "맘 회원 정보")
    @Valid
    val momDetail: MomSave?
)

@Schema(description = "회원 기본 정보")
data class UserSave(
    @Schema(description = "성함")
    @field:NotBlank(message = "이름을 작성해주세요.")
    val name: String,

    @Schema(description = "생일")
    @field:Past(message = "생일은 과거 날짜여야 합니다.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    val birthdate: LocalDate,

    @Schema(description = "성별")
    val gender: Gender,
)

@Schema(description = "계정 정보")
data class AccountSave(
    @Schema(description = "아이디")
    @field:NotBlank(message = "아이디를 작성해주세요.")
    val loginId: String,

    @Schema(description = "비밀번호")
    @field:NotBlank(message = "비밀번호를 작성해주세요.")
    @field:Size(min = PASSWORD_LENGTH, message = "비밀번호는 최소 ${PASSWORD_LENGTH}자 이상이어야 합니다.")
    val password: String,

    @Schema(description = "이메일")
    @field:Email(message = "올바른 이메일 형식이어야 합니다.")
    val email: String
)

@Schema(description = "시터 정보")
data class SitterSave(
    @Schema(description = "자기소개")
    @field:NotBlank(message = "자기소개를 작성해주세요.")
    val introduction: String,
    @Schema(description = "케어 가능 연령 From")
    @field:Min(value = 0, message = "최소 케어 가능 연령은 0 이상이어야 합니다.")
    val carableAgeFrom: Int,
    @Schema(description = "케어 가능 연령 To")
    @field:Min(value = 0, message = "최소 케어 가능 연령은 0 이상이어야 합니다.")
    val carableAgeTo: Int
)

@Schema(description = "맘 회원 정보")
data class MomSave(
    @Schema(description = "아이 정보")
    @field:NotBlank(message = "아이 정보를 작성해주세요.")
    val childNote: String,
    @Schema(description = "신청 내용")
    @field:NotBlank(message = "신청 내용을 작성해주세요.")
    val requestMessage: String
)

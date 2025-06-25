package com.dollee.sitly.user.domain.service

object PasswordPolicy {
    private val SPECIAL_CHAR_REGEX = Regex("[^a-zA-Z0-9]") // 알파벳과 숫자를 제외한 모든 문자
    const val PASSWORD_LENGTH = 7
    fun validate(rawPassword: String) {
        require(rawPassword.length >= PASSWORD_LENGTH) { "비밀번호는 최소 ${PASSWORD_LENGTH}자 이상이어야 합니다." }
        require(rawPassword.any { it.isDigit() }) { "비밀번호에는 최소한 하나의 숫자가 포함되어야 합니다." }
        require(SPECIAL_CHAR_REGEX.containsMatchIn(rawPassword)) { "비밀번호에는 특수문자가 최소 하나 포함되어야 합니다." }
    }
}
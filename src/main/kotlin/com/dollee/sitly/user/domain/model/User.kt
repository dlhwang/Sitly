package com.dollee.sitly.user.domain.model

data class User(
    val id: String? = null,
    val userDetail: UserDetail,
    val accountDetail: AccountDetail,
    val sitter: Sitter? = null,
    val mom: Mom? = null
) {

}
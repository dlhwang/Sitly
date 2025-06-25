package com.dollee.sitly.user.domain.model


data class Child(
    val id: String? = null,
    var userDetail: UserDetail
) {
    fun modify(newDetail: UserDetail) {
        this.userDetail = newDetail
    }
}

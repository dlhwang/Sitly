package com.dollee.sitly.config.security

data class CurrentUserPrincipal(
    val userId: String,
    val role: String
)

package com.dollee.sitly.global.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ErrorResponse(
    val code: String,
    val message: String,
    val status: Int,
    var path: String? = null,
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd HH:mm:ss",
        timezone = "Asia/Seoul"
    )
    val timestamp: LocalDateTime = LocalDateTime.now()
)
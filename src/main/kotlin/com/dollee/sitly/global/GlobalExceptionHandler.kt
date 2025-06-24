package com.dollee.sitly.global

import com.dollee.sitly.global.model.ErrorResponse
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Slf4j
@RestControllerAdvice
class GlobalExceptionHandler {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @ExceptionHandler(Exception::class)
    fun handleAll(ex: Exception): ResponseEntity<ErrorResponse> {
        log.error(ex.message, ex)
        val response = ErrorResponse(
            code = "INTERNAL_SERVER_ERROR",
            message = ex.message ?: "서버 오류가 발생했습니다.",
            status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        )
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
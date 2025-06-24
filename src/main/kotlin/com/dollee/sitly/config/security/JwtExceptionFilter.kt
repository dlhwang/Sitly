package com.dollee.sitly.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.web.ErrorResponse
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.nio.charset.StandardCharsets

class JwtExceptionFilter : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (ex: AuthenticationException) {
            setErrorResponse(ex, response, ex.message ?: "인증 오류 발생")
        }
    }

    private fun setErrorResponse(
        ex: AuthenticationException,
        response: HttpServletResponse,
        message: String
    ) {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        response.setStatus(HttpStatus.UNAUTHORIZED.value())
        response.setContentType(MediaType.APPLICATION_JSON_VALUE)
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString())
        val errorResponse: ErrorResponse =
            ErrorResponse.create(ex, HttpStatus.UNAUTHORIZED, message)
        try {
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

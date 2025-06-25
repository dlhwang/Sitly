package com.dollee.sitly.controller

import com.dollee.sitly.dto.UserRequest
import com.dollee.sitly.dto.UserResponse
import com.dollee.sitly.dto.toCommand
import com.dollee.sitly.service.AuthService
import com.dollee.sitly.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sitly/auth")
class AuthController(
    private val authService: AuthService,
    private val service: UserService
) {
    @Operation(
        summary = "API 로그인",
        description = "로그인시도하여 토큰을 발급받습니다.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = [Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = Schema(implementation = LoginRequest::class)
            )]
        ),
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "조회 성공",
                content = [Content(schema = Schema(implementation = TokenResponse::class))]
            ),
            ApiResponse(responseCode = "404", description = "없음")
        ]
    )
    @PostMapping("/login")
    suspend fun login(@RequestBody request: LoginRequest): ResponseEntity<TokenResponse> {
        val token = authService.login(request.username, request.password)
        return ResponseEntity.ok(TokenResponse(token))
    }

    @Operation(
        summary = "API 회원 정보 등록",
        description = "API 회원 정보를 등록 합니다.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = [Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = Schema(implementation = UserRequest::class)
            )]
        ),
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "등록 성공",
                content = [Content(schema = Schema(implementation = UserResponse::class))]
            ),
            ApiResponse(responseCode = "400", description = "등록 실패")
        ]
    )
    @PostMapping()
    fun post(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(service.register(userRequest.toCommand()))
    }

    @Schema(description = "로그인 요청 객체")
    data class LoginRequest(
        @Schema(description = "사용자 아이디", example = "user")
        val username: String,
        @Schema(description = "사용자 아이디", example = "qwer1234")
        val password: String
    )

    @Schema(description = "로그인 요청 객체")
    data class TokenResponse(val token: String)
}
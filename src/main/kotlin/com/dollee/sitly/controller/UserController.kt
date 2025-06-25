package com.dollee.sitly.controller

import com.dollee.sitly.config.security.AuthUser
import com.dollee.sitly.config.security.CurrentUser
import com.dollee.sitly.controller.AuthController.TokenResponse
import com.dollee.sitly.dto.UserRequest
import com.dollee.sitly.dto.UserResponse
import com.dollee.sitly.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "회원 관리 v1", description = "회원 관리 컨트롤러 입니다.")
@RestController
@RequestMapping("/api/sitly/users")
class UserController(
    private val service: UserService
) {

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
                content = [Content(schema = Schema(implementation = TokenResponse::class))]
            ),
            ApiResponse(responseCode = "400", description = "등록 실패")
        ]
    )
    @PostMapping()
    fun post(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(service.register(userRequest))
    }


    @Operation(
        summary = "API 회원 본인 정보",
        description = "API 회원 본인 정보를 반환합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "조회 성공",
                content = [Content(schema = Schema(implementation = TokenResponse::class))]
            ),
            ApiResponse(responseCode = "404", description = "")
        ]
    )
    @GetMapping("/me")
    fun getMyInfo(@Parameter(hidden = true) @CurrentUser user: AuthUser): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(service.getMyInfo(user.username))
    }
}
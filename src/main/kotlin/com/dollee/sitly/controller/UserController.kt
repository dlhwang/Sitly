package com.dollee.sitly.controller

import com.dollee.sitly.config.security.AuthUser
import com.dollee.sitly.config.security.CurrentUser
import com.dollee.sitly.dto.*
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
                content = [Content(schema = Schema(implementation = UserResponse::class))]
            ),
            ApiResponse(responseCode = "400", description = "등록 실패")
        ]
    )
    @PostMapping()
    fun post(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(service.register(userRequest.toCommand()))
    }


    @Operation(
        summary = "API 회원 본인 정보",
        description = "API 회원 본인 정보를 반환합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "조회 성공",
                content = [Content(schema = Schema(implementation = UserResponse::class))]
            ),
            ApiResponse(responseCode = "404", description = "")
        ]
    )
    @GetMapping("/me")
    fun getMyInfo(@Parameter(hidden = true) @CurrentUser user: AuthUser): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(service.getMyInfo(user.username))
    }

    @Operation(
        summary = "API 회원 정보 수정",
        description = "API 회원 정보를 수정 합니다.",
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
    @PutMapping()
    fun put(
        @RequestBody userRequest: UserRequest,
        @Parameter(hidden = true) @CurrentUser user: AuthUser
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(service.modify(user.username, userRequest.toCommand()))
    }


    @Operation(
        summary = "API 회원 기본 정보 수정",
        description = "API 회원 > 회원 기본 정보를 수정 합니다.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = [Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = Schema(implementation = UserSave::class)
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
    @PatchMapping("/userDetail")
    fun put(
        @RequestBody request: UserSave,
        @Parameter(hidden = true) @CurrentUser user: AuthUser
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(service.modify(user.username, request.toCommand()))
    }

    @Operation(
        summary = "API 회원 계정 정보 수정",
        description = "API 회원 > 회원 계정 정보를 수정 합니다.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = [Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = Schema(implementation = AccountSave::class)
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
    @PatchMapping("/accountDetail")
    fun put(
        @RequestBody request: AccountSave,
        @Parameter(hidden = true) @CurrentUser user: AuthUser
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(service.modify(user.username, request.toCommand()))
    }

    @Operation(
        summary = "API 회원 시터 정보 수정",
        description = "API 회원 > 회원 시터 정보를 수정 합니다.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = [Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = Schema(implementation = SitterSave::class)
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
    @PatchMapping("/sitterDetail")
    fun put(
        @RequestBody request: SitterSave,
        @Parameter(hidden = true) @CurrentUser user: AuthUser
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(service.modify(user.username, request.toCommand()))
    }

    @Operation(
        summary = "API 맘 회원 정보 수정",
        description = "API 회원 > 맘 정보를 수정 합니다.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = [Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = Schema(implementation = MomSave::class)
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
    @PatchMapping("/momDetail")
    fun put(
        @RequestBody request: MomSave,
        @Parameter(hidden = true) @CurrentUser user: AuthUser
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(service.modify(user.username, request.toCommand()))
    }
}
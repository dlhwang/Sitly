package com.dollee.sitly.controller

import com.dollee.sitly.service.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AuthControllerTest(
    @Autowired private val authService: AuthService
) {

    @Autowired
    lateinit var webTestClient: WebTestClient


    @Test
    fun login_success_returns_token(): Unit = runBlocking {
        val loginRequest = mapOf(
            "username" to "dollee",
            "password" to "qwer1234"
        )

        webTestClient.post()
            .uri("/api/sitly/auth/login")
            .bodyValue(loginRequest)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.token").isNotEmpty
    }

    @Test
    fun `100회 비동기 로그인 시도`() = runBlocking {
        val username = "dollee"
        val password = "qwer1234"

        val deferredResults = (1..100).map {
            async(Dispatchers.IO) {
                try {
                    authService.login(username, password)
                } catch (e: Exception) {
                    null
                }
            }
        }

        val tokens = deferredResults.awaitAll().filterNotNull()

        // 모두 토큰을 받았는지 확인
        assertEquals(100, tokens.size)

        tokens.forEach { token ->
            assertTrue(token.isNotBlank())
        }
    }
}

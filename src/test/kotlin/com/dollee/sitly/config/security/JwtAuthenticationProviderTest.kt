package com.dollee.sitly.config.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JwtAuthenticationProviderTest {
    private lateinit var jwtProvider: JwtAuthenticationProvider
    private lateinit var secretKey: String

    @BeforeAll
    fun setup() {
        // 32바이트 SHA-256 인코딩된 키를 Base64로 인코딩한 값 (테스트용)
        secretKey = "c2l0bHktYXBpLWFjY2Vzc2VzLXNlY3JldC1rZXktMTE5"
        jwtProvider = JwtAuthenticationProvider(secretKey, secretKey, 10)
    }

    @Test
    fun `generateJwtToken should return valid token`() {
        val user: UserDetails = User.withUsername("testuser")
            .password("password")
            .roles("USER")
            .build()

        val token = jwtProvider.generateJwtToken(user)
        assertTrue(token.isNotBlank(), "JWT 토큰이 발급되어야 합니다.")
    }

    @Test
    fun `getAuthentication should parse token and return Authentication`() {
        val user: UserDetails = User.withUsername("testuser")
            .password("password")
            .roles("USER")
            .build()

        val token = jwtProvider.generateJwtToken(user)

        val auth = jwtProvider.getAuthentication(token)
        assertEquals("testuser", (auth.principal as AuthUser).username)
        assertTrue(auth.isAuthenticated)
    }

    @Test
    fun `getSubject should return correct subject from token`() {
        val token = Jwts.builder()
            .setSubject("sitly-user")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 60000))
            .signWith(
                jwtProvider.run { Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey)) },
                SignatureAlgorithm.HS256
            )
            .compact()

        val subject = jwtProvider.run {
            val method = this::class.java.getDeclaredMethod("getSubject", String::class.java)
            method.isAccessible = true
            method.invoke(this, token) as String
        }

        assertEquals("sitly-user", subject)
    }

    @Test
    fun `isValidToken should return true for valid token`() {
        val user: UserDetails = User.withUsername("validuser")
            .password("pass")
            .roles("USER")
            .build()

        val token = jwtProvider.generateJwtToken(user)
        assertTrue(jwtProvider.isValidToken(token))
    }

    @AfterAll
    fun tearDown() {
        unmockkAll()
    }

}
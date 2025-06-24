package com.dollee.sitly.global.utills

import org.junit.jupiter.api.Assertions.assertTrue
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import kotlin.test.Test

class PasswordEncoderTest {
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
    private val log = LoggerFactory.getLogger(this::class.java)

    @Test
    fun `비밀번호 인코드는 인코딩된다`() {
        val password = "qwer1234"
        val encoded = passwordEncoder.encode(password)
        log.info("{} {}", password, encoded)
        assertTrue(passwordEncoder.matches(password, encoded), "다름 $encoded")
    }
}
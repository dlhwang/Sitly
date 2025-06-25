package com.dollee.sitly.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest @Autowired constructor(
    val mockMvc: MockMvc
) {
    @Test
    fun login_success_returns_token() {

        val json = """
        {
          "username": "user",
          "password": "qwer1234"
        }
        """.trimIndent()

        mockMvc.perform(
            post("/api/sitly/auth/login") // 이 post()는 확장 함수 아님 (주의)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json) // 여기서 에러 났다면, import 확인
        ).andExpect(status().is2xxSuccessful)
    }
}

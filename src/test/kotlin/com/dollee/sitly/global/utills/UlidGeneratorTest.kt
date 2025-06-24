package com.dollee.sitly.global.utills

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.slf4j.LoggerFactory
import kotlin.test.Test

class UlidGeneratorTest {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Test
    fun `ULID는 26자리 문자열이고 중복되지 않는다`() {
        val generator = UlidGenerator()
        val ulids = mutableSetOf<String>()

        repeat(1000) {
            val ulid = generator.generate(mockk(), Any()) as String
            log.info("Generate {}", ulid)
            assertEquals(26, ulid.length)
            assertTrue(ulid.matches(Regex("[0-9A-Z]{26}")), "ULID 형식이 아님: $ulid")
            assertTrue(ulids.add(ulid), "ULID가 중복됨: $ulid")
        }
    }

    @Test
    fun tmep() {
        val keyBytes = "sitly-api-accesses-secret-key-119".toByteArray()
        println(keyBytes.size) // 출력: 29
    }

}
package com.dollee.sitly.config

import com.dollee.sitly.user.domain.model.*
import com.dollee.sitly.user.domain.model.enumtype.Gender
import com.dollee.sitly.user.domain.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DataInitializer(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        if (userRepository.count() == 0L) {
            val user = User(
                userDetail = UserDetail(
                    name = "사용자",
                    birthdate = LocalDate.now(),
                    gender = Gender.남
                ),
                accountDetail = AccountDetail(
                    loginId = LoginId("user"),
                    password = EncryptedPassword(passwordEncoder.encode("qwer1234")),
                    email = Email("user@email.com")
                )
            )
            userRepository.save(user)
        }
    }
}

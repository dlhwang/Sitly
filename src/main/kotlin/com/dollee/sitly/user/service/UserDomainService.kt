package com.dollee.sitly.user.service

import com.dollee.sitly.service.*
import com.dollee.sitly.user.domain.model.*
import com.dollee.sitly.user.domain.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDomainService(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {
    fun getMyInfo(loginId: String): User {
        val user = userRepository.findByLoginId(loginId)
        return user.id?.let { userRepository.findById(it) }
            ?: throw IllegalArgumentException("로그인 아이디의 USER ID가 없습니다 : $loginId")
    }

    fun register(command: RegisterUserCommand): User {
        return userRepository.save(convert(null, command))
    }

    fun modify(loginId: String, command: RegisterUserCommand): User {
        return userRepository.save(convert(getMyInfo(loginId).id, command))
    }

    fun modify(loginId: String, command: UserInput): User {
        return userRepository.save(loginId, convert(command))
    }

    fun modify(loginId: String, command: AccountInput): User {
        return userRepository.save(loginId, convert(command))
    }

    fun modify(loginId: String, command: SitterInput): User {
        return userRepository.save(loginId, convert(command))
    }

    fun modify(loginId: String, command: MomInput): User {
        return userRepository.save(loginId, convert(command))
    }

    fun convert(id: String?, command: RegisterUserCommand): User = User(
        id = id,
        userDetail = UserDetail(
            name = command.user.name,
            birthdate = command.user.birthdate,
            gender = command.user.gender
        ),
        accountDetail = AccountDetail(
            loginId = LoginId(command.account.loginId),
            password = EncryptedPassword.fromRaw(command.account.password, passwordEncoder),
            email = Email(command.account.email)
        ),
        sitter = command.sitter?.let {
            Sitter(
                introduction = it.introduction,
                carableAgeFrom = it.carableAgeFrom,
                carableAgeTO = it.carableAgeTo
            )
        },
        mom = command.mom?.let {
            Mom(
                childNote = it.childNote,
                requestMessage = it.requestMessage
            )
        }
    )

    fun convert(command: UserInput): UserDetail = UserDetail(
        name = command.name,
        birthdate = command.birthdate,
        gender = command.gender
    )

    fun convert(command: AccountInput): AccountDetail = AccountDetail(
        loginId = LoginId(command.loginId),
        password = EncryptedPassword.fromRaw(command.password, passwordEncoder),
        email = Email(command.email)
    )

    fun convert(command: SitterInput): Sitter = Sitter(
        introduction = command.introduction,
        carableAgeFrom = command.carableAgeFrom,
        carableAgeTO = command.carableAgeTo,
    )

    fun convert(command: MomInput): Mom = Mom(
        childNote = command.childNote,
        requestMessage = command.requestMessage,
    )
}
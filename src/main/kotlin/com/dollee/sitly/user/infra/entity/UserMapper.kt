package com.dollee.sitly.user.infra.entity

import com.dollee.sitly.user.domain.model.User

object UserMapper {
    fun toEntity(domain: User): UserEntity =
        UserEntity(
            id = domain.id,
            name = domain.name,
            birthdate = domain.birthdate,
            gender = domain.gender,
            loginId = domain.loginId,
            password = domain.password
        )

    fun toDomain(entity: UserEntity): User =
        User(
            id = entity.id,
            name = entity.name,
            birthdate = entity.birthdate,
            gender = entity.gender,
            loginId = entity.loginId,
            password = entity.password
        )
}
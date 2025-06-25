package com.dollee.sitly.user.infra.entity

import com.dollee.sitly.user.domain.model.*
import com.dollee.sitly.user.domain.model.AccountDetail
import com.dollee.sitly.user.domain.model.UserDetail

object UserMapper {
    fun toEntity(domain: User): UserEntity =
        UserEntity(
            id = domain.id,
            userDetail = toEntity(domain.userDetail),
            accountDetail = toEntity(domain.accountDetail)
        )

    fun toDomain(entity: UserEntity, sitterEntity: SitterEntity?, momEntity: MomEntity?): User =
        User(
            id = entity.id,
            userDetail = toDomain(entity.getUserDetail()),
            accountDetail = toDomain(entity.getAccountDetail()),
            sitter = sitterEntity?.let { toDomain(it) },
            mom = momEntity?.let { toDomain(it) },
        )

    fun toDomain(entity: MomEntity): Mom = Mom(
        requestMessage = entity.getRequestMessage(),
        child = entity.getChildRen().map { toDomain(it) }.toMutableList()
    )

    fun toEntity(user: UserEntity, mom: Mom): MomEntity {
        val momEntity = MomEntity(
            user = user,
            requestMessage = mom.requestMessage
        )

        mom.getChildren()
            .map { toEntity(it) }
            .forEach { momEntity.addChild(it) }

        return momEntity
    }

    fun toDomain(entity: SitterEntity): Sitter = Sitter(
        introduction = entity.getIntroduction(),
        carableAgeFrom = entity.getCarableAgeFrom(),
        carableAgeTO = entity.getCarableAgeTo()
    )

    fun toEntity(user: UserEntity, sitter: Sitter): SitterEntity {
        return SitterEntity(
            user = user,
            introduction = sitter.introduction,
            carableAgeFrom = sitter.carableAgeFrom,
            carableAgeTO = sitter.carableAgeTO
        )
    }

    fun toDomain(userDetail: com.dollee.sitly.user.infra.entity.UserDetail): UserDetail =
        UserDetail(
            name = userDetail.name,
            birthdate = userDetail.birthdate,
            gender = userDetail.gender
        )

    fun toEntity(userDetail: UserDetail): com.dollee.sitly.user.infra.entity.UserDetail =
        com.dollee.sitly.user.infra.entity.UserDetail(
            name = userDetail.name,
            birthdate = userDetail.birthdate,
            gender = userDetail.gender
        )

    fun toDomain(entity: com.dollee.sitly.user.infra.entity.AccountDetail): AccountDetail =
        AccountDetail(
            loginId = entity.loginId,
            password = entity.password,
            email = entity.email
        )

    fun toEntity(domain: AccountDetail): com.dollee.sitly.user.infra.entity.AccountDetail =
        com.dollee.sitly.user.infra.entity.AccountDetail(
            loginId = domain.loginId,
            password = domain.password,
            email = domain.email
        )

    fun toDomain(entity: ChildEntity): Child = Child(
        id = entity.id,
        userDetail = toDomain(entity.getUserDetail()),
    )

    fun toEntity(domain: Child): ChildEntity = ChildEntity(
        id = domain.id,
        userDetail = toEntity(domain.userDetail),
    )
}
package com.dollee.sitly.user.infra.repository

import com.dollee.sitly.global.exception.DataNotFoundException
import com.dollee.sitly.user.domain.model.*
import com.dollee.sitly.user.domain.repository.UserRepository
import com.dollee.sitly.user.infra.entity.MomEntity
import com.dollee.sitly.user.infra.entity.SitterEntity
import com.dollee.sitly.user.infra.entity.UserEntity
import com.dollee.sitly.user.infra.entity.UserMapper
import com.dollee.sitly.user.infra.entity.UserMapper.toEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class JpaUserRepository(
    val repository: UserJpaRepository,
    val sitterJpaRepository: SitterJpaRepository,
    val momJpaRepository: MomJpaRepository
) : UserRepository {
    override fun findByLoginId(loginId: String): User {
        return repository.findByAccountDetail_LoginId(loginId)
            ?.let { UserMapper.toDomain(it, null, null) }
            ?: throw DataNotFoundException("해당 사용자가 없습니다: $loginId");
    }

    @Transactional
    override fun save(user: User): User {
        val userEntity = toEntity(user)
        val savedUser = repository.save(userEntity)
        repository.flush()
        print(savedUser.id)
        user.sitter?.let {
            val existing = savedUser.id?.let { id -> sitterJpaRepository.findByUser_Id(id) }
            existing?.modify(it.introduction, it.carableAgeFrom, it.carableAgeTO)
                ?: sitterJpaRepository.save(toEntity(savedUser, it))
        }
        user.mom?.let {
            val existing = savedUser.id?.let { id -> momJpaRepository.findByUser_Id(id) }
            if (existing != null) {
                existing.modifyRequestMessage(it.requestMessage)
                existing.addChild(it.getChildren().map { c -> toEntity(c) })
            } else {
                momJpaRepository.save(toEntity(savedUser, it))
            }
        }

        return savedUser.id?.let { findById(it) }
            ?: throw IllegalStateException("ID가 null이므로 조회할 수 없습니다.")
    }

    override fun count(): Long {
        return repository.count()
    }

    override fun findById(id: String): User {
        val result = repository.findUserWithSitterAndMomById(id).firstOrNull()
            ?: throw DataNotFoundException("해당 사용자가 없습니다: ${id}")
        val user = result[0] as UserEntity
        val sitter = result.getOrNull(1) as? SitterEntity
        val mom = result.getOrNull(2) as? MomEntity

        return UserMapper.toDomain(user, sitter, mom)
    }

    override fun save(
        loginId: String,
        userDetail: UserDetail
    ): User {
        val user = findByLoginId(loginId);
        val updatedUser = user.copy(userDetail = userDetail)
        return UserMapper.toDomain(
            repository.save(UserMapper.toEntity(updatedUser)),
            null,
            null
        )
    }

    override fun save(
        loginId: String,
        account: AccountDetail
    ): User {
        val user = findByLoginId(loginId);
        val updatedUser = user.copy(accountDetail = account)
        return UserMapper.toDomain(
            repository.save(UserMapper.toEntity(updatedUser)),
            null,
            null
        )
    }


    override fun save(
        loginId: String,
        sitter: Sitter
    ): User {
        val entity = sitterJpaRepository.findByUser_AccountDetail_LoginId(LoginId(loginId))
            ?: throw DataNotFoundException("해당 사용자가 없습니다: ${loginId}")
        entity.modify(sitter.introduction, sitter.carableAgeFrom, sitter.carableAgeTO)
        val save = sitterJpaRepository.save(entity)
        val id = save.user.id ?: throw IllegalStateException("저장된 Sitter의 ID가 null입니다.")
        return findById(id)
    }


    override fun save(
        loginId: String,
        mom: Mom
    ): User {
        val entity = momJpaRepository.findByUser_AccountDetail_LoginId(LoginId(loginId))
            ?: throw DataNotFoundException("해당 사용자가 없습니다: $loginId")
        entity.modifyRequestMessage(mom.requestMessage)

        entity.clearChildren()
        entity.addChild(mom.getChildren().map { it -> toEntity(it) })

        val save = momJpaRepository.save(entity)
        val id = save.user.id ?: throw IllegalStateException("저장된 Sitter의 ID가 null입니다.")
        return findById(id)
    }
}

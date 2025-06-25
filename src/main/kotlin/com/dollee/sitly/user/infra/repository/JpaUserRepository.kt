package com.dollee.sitly.user.infra.repository

import com.dollee.sitly.global.exception.DataNotFoundException
import com.dollee.sitly.user.domain.model.User
import com.dollee.sitly.user.domain.repository.UserRepository
import com.dollee.sitly.user.infra.entity.MomEntity
import com.dollee.sitly.user.infra.entity.SitterEntity
import com.dollee.sitly.user.infra.entity.UserEntity
import com.dollee.sitly.user.infra.entity.UserMapper
import org.springframework.stereotype.Repository

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

    override fun save(user: User): User {
        val save = repository.save(UserMapper.toEntity(user))
        user.sitter?.let { sitterJpaRepository.save(UserMapper.toEntity(user, it)) }
        user.mom?.let { momJpaRepository.save(UserMapper.toEntity(user, it)) }
        return save.id?.let { findById(it) }
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
}

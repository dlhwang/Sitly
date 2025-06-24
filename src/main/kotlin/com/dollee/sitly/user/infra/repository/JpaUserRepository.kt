package com.dollee.sitly.user.infra.repository

import com.dollee.sitly.global.exception.DataNotFoundException
import com.dollee.sitly.user.domain.model.User
import com.dollee.sitly.user.domain.repository.UserRepository
import com.dollee.sitly.user.infra.entity.UserMapper
import org.springframework.stereotype.Repository

@Repository
class JpaUserRepository(
    val repository: UserJpaRepository
) : UserRepository {
    override fun findByLoginId(loginId: String): User {
        return repository.findByLoginId(loginId)
            ?: throw DataNotFoundException("해당 인원이 없습니다: $loginId");
    }

    override fun save(user: User): User {
        return UserMapper.toDomain(repository.save(UserMapper.toEntity(user)))
    }

    override fun count(): Long {
        return repository.count()
    }
}

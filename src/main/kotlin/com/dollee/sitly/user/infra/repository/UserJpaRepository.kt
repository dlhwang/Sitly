package com.dollee.sitly.user.infra.repository

import com.dollee.sitly.user.domain.model.User
import com.dollee.sitly.user.infra.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<UserEntity, String> {
    fun findByLoginId(loginId: String): User?
}
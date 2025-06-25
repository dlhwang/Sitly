package com.dollee.sitly.user.infra.repository

import com.dollee.sitly.user.infra.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserJpaRepository : JpaRepository<UserEntity, String> {
    fun findByAccountDetail_LoginId(loginId: String): UserEntity?

    @Query(
        """
        SELECT u, s, m
        FROM UserEntity u
        LEFT JOIN SitterEntity s ON s.user.id = u.id
        LEFT JOIN MomEntity m ON m.user.id = u.id
        WHERE u.id = :userId
    """
    )
    fun findUserWithSitterAndMomById(@Param("userId") userId: String): List<Array<Any>>
}
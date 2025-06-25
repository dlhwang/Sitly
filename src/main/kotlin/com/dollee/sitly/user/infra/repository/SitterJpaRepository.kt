package com.dollee.sitly.user.infra.repository

import com.dollee.sitly.user.domain.model.LoginId
import com.dollee.sitly.user.infra.entity.SitterEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SitterJpaRepository : JpaRepository<SitterEntity, String> {
    fun findByUser_AccountDetail_LoginId(loginId: LoginId): SitterEntity?
    fun findByUser_Id(id: String): SitterEntity?
}
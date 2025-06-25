package com.dollee.sitly.user.infra.repository

import com.dollee.sitly.user.domain.model.LoginId
import com.dollee.sitly.user.infra.entity.MomEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MomJpaRepository : JpaRepository<MomEntity, String> {
    fun findByUser_AccountDetail_LoginId(loginId: LoginId): MomEntity?
}
package com.dollee.sitly.user.infra.repository

import com.dollee.sitly.user.infra.entity.SitterEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SitterJpaRepository : JpaRepository<SitterEntity, String> {
}
package com.dollee.sitly.user.domain.repository

import com.dollee.sitly.user.domain.model.User

interface UserRepository {
    fun findByLoginId(loginId: String): User
    fun save(user: User): User
    fun count(): Long
    fun findById(id: String): User
}
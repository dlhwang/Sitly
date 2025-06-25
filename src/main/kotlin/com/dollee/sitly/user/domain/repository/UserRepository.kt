package com.dollee.sitly.user.domain.repository

import com.dollee.sitly.user.domain.model.*

interface UserRepository {
    fun findByLoginId(loginId: String): User
    fun save(user: User): User
    fun count(): Long
    fun findById(id: String): User
    fun save(loginId: String, userDetail: UserDetail): User
    fun save(loginId: String, account: AccountDetail): User
    fun save(loginId: String, sitter: Sitter): User
    fun save(loginId: String, mom: Mom): User
}
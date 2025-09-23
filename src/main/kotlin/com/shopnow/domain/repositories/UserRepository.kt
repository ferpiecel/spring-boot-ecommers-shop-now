package com.shopnow.domain.repositories

import com.shopnow.domain.entities.User
import com.shopnow.domain.valueobjects.Email
import com.shopnow.domain.valueobjects.UserId

/**
 * User repository port - Interface for User persistence
 */
interface UserRepository {
    suspend fun save(user: User): User
    suspend fun findById(id: UserId): User?
    suspend fun findByEmail(email: Email): User?
    suspend fun findByUsername(username: String): User?
    suspend fun existsByEmail(email: Email): Boolean
    suspend fun existsByUsername(username: String): Boolean
    suspend fun deleteById(id: UserId)
}
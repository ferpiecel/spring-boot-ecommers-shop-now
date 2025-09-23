package com.shopnow.domain.repositories

import com.shopnow.domain.entities.Cart
import com.shopnow.domain.valueobjects.CartId
import com.shopnow.domain.valueobjects.UserId

/**
 * Cart repository port - Interface for Cart persistence
 */
interface CartRepository {
    suspend fun save(cart: Cart): Cart
    suspend fun findById(id: CartId): Cart?
    suspend fun findByUserId(userId: UserId): Cart?
    suspend fun deleteById(id: CartId)
    suspend fun deleteByUserId(userId: UserId)
}
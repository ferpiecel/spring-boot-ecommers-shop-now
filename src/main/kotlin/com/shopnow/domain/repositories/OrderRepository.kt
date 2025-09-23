package com.shopnow.domain.repositories

import com.shopnow.domain.entities.Order
import com.shopnow.domain.valueobjects.OrderId
import com.shopnow.domain.valueobjects.OrderStatus
import com.shopnow.domain.valueobjects.UserId

/**
 * Order repository port - Interface for Order persistence
 */
interface OrderRepository {
    suspend fun save(order: Order): Order
    suspend fun findById(id: OrderId): Order?
    suspend fun findByUserId(userId: UserId): List<Order>
    suspend fun findByStatus(status: OrderStatus): List<Order>
    suspend fun findByUserIdAndStatus(userId: UserId, status: OrderStatus): List<Order>
    suspend fun deleteById(id: OrderId)
}
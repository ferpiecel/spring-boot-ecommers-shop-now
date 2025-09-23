package com.shopnow.domain.entities

import com.shopnow.domain.valueobjects.*
import java.time.LocalDateTime

/**
 * Shopping Cart domain entity - Aggregate Root
 */
data class Cart(
    val id: CartId,
    val userId: UserId,
    val items: List<CartItem>,
    val creationDate: LocalDateTime
) {
    val totalAmount: Money
        get() = items.fold(Money.ZERO) { acc, item -> acc + item.totalPrice }

    val totalItems: Int
        get() = items.sumOf { it.quantity.value }

    val isEmpty: Boolean
        get() = items.isEmpty()

    fun addItem(productId: ProductId, quantity: Quantity, unitPrice: Money): Cart {
        val existingItem = items.find { it.productId == productId }
        
        return if (existingItem != null) {
            val updatedItems = items.map { item ->
                if (item.productId == productId) {
                    item.copy(quantity = item.quantity + quantity)
                } else {
                    item
                }
            }
            copy(items = updatedItems)
        } else {
            val newItem = CartItem.create(id, productId, quantity, unitPrice)
            copy(items = items + newItem)
        }
    }

    fun removeItem(productId: ProductId): Cart {
        return copy(items = items.filter { it.productId != productId })
    }

    fun updateItemQuantity(productId: ProductId, newQuantity: Quantity): Cart {
        if (newQuantity.isZero()) {
            return removeItem(productId)
        }
        
        val updatedItems = items.map { item ->
            if (item.productId == productId) {
                item.copy(quantity = newQuantity)
            } else {
                item
            }
        }
        return copy(items = updatedItems)
    }

    fun clear(): Cart {
        return copy(items = emptyList())
    }

    companion object {
        fun create(userId: UserId): Cart {
            return Cart(
                id = CartId.generate(),
                userId = userId,
                items = emptyList(),
                creationDate = LocalDateTime.now()
            )
        }
    }
}

/**
 * Cart Item entity
 */
data class CartItem(
    val id: String,
    val cartId: CartId,
    val productId: ProductId,
    val quantity: Quantity,
    val unitPrice: Money
) {
    val totalPrice: Money
        get() = unitPrice * quantity.value

    companion object {
        fun create(
            cartId: CartId,
            productId: ProductId,
            quantity: Quantity,
            unitPrice: Money
        ): CartItem {
            return CartItem(
                id = java.util.UUID.randomUUID().toString(),
                cartId = cartId,
                productId = productId,
                quantity = quantity,
                unitPrice = unitPrice
            )
        }
    }
}
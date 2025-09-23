package com.shopnow.domain.entities

import com.shopnow.domain.valueobjects.ProductId
import com.shopnow.domain.valueobjects.UserId
import com.shopnow.domain.valueobjects.WishlistId
import java.time.LocalDateTime

/**
 * Wishlist domain entity - Aggregate Root
 */
data class Wishlist(
    val id: WishlistId,
    val userId: UserId,
    val items: List<WishlistItem>,
    val wishlistDate: LocalDateTime
) {
    val isEmpty: Boolean
        get() = items.isEmpty()

    fun addItem(productId: ProductId): Wishlist {
        if (items.any { it.productId == productId }) {
            return this // Item already exists
        }
        
        val newItem = WishlistItem.create(id, productId)
        return copy(items = items + newItem)
    }

    fun removeItem(productId: ProductId): Wishlist {
        return copy(items = items.filter { it.productId != productId })
    }

    fun containsProduct(productId: ProductId): Boolean {
        return items.any { it.productId == productId }
    }

    companion object {
        fun create(userId: UserId): Wishlist {
            return Wishlist(
                id = WishlistId.generate(),
                userId = userId,
                items = emptyList(),
                wishlistDate = LocalDateTime.now()
            )
        }
    }
}

/**
 * Wishlist Item entity
 */
data class WishlistItem(
    val id: String,
    val wishlistId: WishlistId,
    val productId: ProductId
) {
    companion object {
        fun create(wishlistId: WishlistId, productId: ProductId): WishlistItem {
            return WishlistItem(
                id = java.util.UUID.randomUUID().toString(),
                wishlistId = wishlistId,
                productId = productId
            )
        }
    }
}
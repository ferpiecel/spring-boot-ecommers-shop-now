package com.shopnow.domain.entities

import com.shopnow.domain.valueobjects.InventoryId
import com.shopnow.domain.valueobjects.ProductId
import com.shopnow.domain.valueobjects.Quantity
import java.time.LocalDateTime

/**
 * Inventory Management domain entity
 */
data class Inventory(
    val id: InventoryId,
    val productId: ProductId,
    val stockIn: Quantity,
    val stockOut: Quantity,
    val lastUpdate: LocalDateTime
) {
    val currentStock: Quantity
        get() = stockIn - stockOut

    fun addStock(quantity: Quantity): Inventory {
        return copy(
            stockIn = stockIn + quantity,
            lastUpdate = LocalDateTime.now()
        )
    }

    fun removeStock(quantity: Quantity): Inventory {
        return copy(
            stockOut = stockOut + quantity,
            lastUpdate = LocalDateTime.now()
        )
    }

    companion object {
        fun create(productId: ProductId, initialStock: Quantity): Inventory {
            return Inventory(
                id = InventoryId.generate(),
                productId = productId,
                stockIn = initialStock,
                stockOut = Quantity.ZERO,
                lastUpdate = LocalDateTime.now()
            )
        }
    }
}
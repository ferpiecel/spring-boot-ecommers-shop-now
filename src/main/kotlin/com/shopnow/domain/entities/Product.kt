package com.shopnow.domain.entities

import com.shopnow.domain.valueobjects.*

/**
 * Product domain entity - Aggregate Root
 */
data class Product(
    val id: ProductId,
    val name: String,
    val description: String?,
    val price: Money,
    val stockQuantity: Quantity,
    val categoryId: CategoryId,
    val images: List<ProductImage> = emptyList()
) {
    init {
        require(name.isNotBlank()) { "Product name cannot be blank" }
    }

    val isInStock: Boolean
        get() = stockQuantity.isPositive()

    fun updateStock(newQuantity: Quantity): Product {
        return copy(stockQuantity = newQuantity)
    }

    fun reduceStock(quantity: Quantity): Product {
        require(stockQuantity.value >= quantity.value) { 
            "Insufficient stock. Available: ${stockQuantity.value}, Requested: ${quantity.value}" 
        }
        return copy(stockQuantity = stockQuantity - quantity)
    }

    fun addStock(quantity: Quantity): Product {
        return copy(stockQuantity = stockQuantity + quantity)
    }

    fun updatePrice(newPrice: Money): Product {
        return copy(price = newPrice)
    }

    fun addImage(image: ProductImage): Product {
        return copy(images = images + image)
    }

    companion object {
        fun create(
            name: String,
            description: String?,
            price: Money,
            stockQuantity: Quantity,
            categoryId: CategoryId
        ): Product {
            return Product(
                id = ProductId.generate(),
                name = name,
                description = description,
                price = price,
                stockQuantity = stockQuantity,
                categoryId = categoryId
            )
        }
    }
}

/**
 * Product Image entity
 */
data class ProductImage(
    val id: String,
    val productId: ProductId,
    val imageUrl: String,
    val altText: String?
) {
    init {
        require(imageUrl.isNotBlank()) { "Image URL cannot be blank" }
    }
}
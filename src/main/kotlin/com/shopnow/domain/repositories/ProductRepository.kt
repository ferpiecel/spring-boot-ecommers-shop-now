package com.shopnow.domain.repositories

import com.shopnow.domain.entities.Product
import com.shopnow.domain.valueobjects.CategoryId
import com.shopnow.domain.valueobjects.ProductId

/**
 * Product repository port - Interface for Product persistence
 */
interface ProductRepository {
    suspend fun save(product: Product): Product
    suspend fun findById(id: ProductId): Product?
    suspend fun findByCategory(categoryId: CategoryId): List<Product>
    suspend fun findAll(): List<Product>
    suspend fun findByNameContaining(name: String): List<Product>
    suspend fun findInStock(): List<Product>
    suspend fun deleteById(id: ProductId)
}
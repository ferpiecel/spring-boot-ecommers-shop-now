package com.shopnow.domain.entities

import com.shopnow.domain.valueobjects.CategoryId

/**
 * Category domain entity
 */
data class Category(
    val id: CategoryId,
    val name: String,
    val description: String?
) {
    init {
        require(name.isNotBlank()) { "Category name cannot be blank" }
    }

    companion object {
        fun create(name: String, description: String?): Category {
            return Category(
                id = CategoryId.generate(),
                name = name,
                description = description
            )
        }
    }
}
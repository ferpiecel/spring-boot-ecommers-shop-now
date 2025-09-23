package com.shopnow.domain.valueobjects

/**
 * Order status enumeration
 */
enum class OrderStatus(val value: String) {
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    PROCESSING("PROCESSING"),
    SHIPPED("SHIPPED"),
    DELIVERED("DELIVERED"),
    CANCELLED("CANCELLED"),
    RETURNED("RETURNED");

    companion object {
        fun fromString(value: String): OrderStatus {
            return entries.find { it.value.equals(value, ignoreCase = true) }
                ?: throw IllegalArgumentException("Invalid order status: $value")
        }
    }
}
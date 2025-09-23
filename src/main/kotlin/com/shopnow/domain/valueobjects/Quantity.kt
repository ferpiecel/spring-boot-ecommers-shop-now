package com.shopnow.domain.valueobjects

/**
 * Quantity value object for inventory and cart items
 */
@JvmInline
value class Quantity(val value: Int) {
    init {
        require(value >= 0) { "Quantity cannot be negative" }
    }

    operator fun plus(other: Quantity): Quantity = Quantity(value + other.value)
    operator fun minus(other: Quantity): Quantity {
        require(value >= other.value) { "Cannot result in negative quantity" }
        return Quantity(value - other.value)
    }

    fun isZero(): Boolean = value == 0
    fun isPositive(): Boolean = value > 0

    companion object {
        val ZERO = Quantity(0)
        val ONE = Quantity(1)
    }
}
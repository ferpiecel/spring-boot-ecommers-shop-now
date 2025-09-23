package com.shopnow.domain.valueobjects

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

/**
 * Money value object representing monetary amounts with currency
 */
data class Money(
    val amount: BigDecimal,
    val currency: Currency = Currency.getInstance("USD")
) {
    init {
        require(amount >= BigDecimal.ZERO) { "Money amount cannot be negative" }
    }

    val scaledAmount: BigDecimal
        get() = amount.setScale(2, RoundingMode.HALF_UP)

    operator fun plus(other: Money): Money {
        require(currency == other.currency) { "Cannot add money with different currencies" }
        return Money(amount + other.amount, currency)
    }

    operator fun minus(other: Money): Money {
        require(currency == other.currency) { "Cannot subtract money with different currencies" }
        require(amount >= other.amount) { "Cannot result in negative money" }
        return Money(amount - other.amount, currency)
    }

    operator fun times(multiplier: BigDecimal): Money {
        require(multiplier >= BigDecimal.ZERO) { "Multiplier cannot be negative" }
        return Money(amount * multiplier, currency)
    }

    operator fun times(multiplier: Int): Money {
        require(multiplier >= 0) { "Multiplier cannot be negative" }
        return times(BigDecimal(multiplier))
    }

    fun isZero(): Boolean = amount.compareTo(BigDecimal.ZERO) == 0

    companion object {
        val ZERO = Money(BigDecimal.ZERO)
        
        fun of(amount: String, currency: Currency = Currency.getInstance("USD")): Money {
            return Money(BigDecimal(amount), currency)
        }
        
        fun of(amount: Double, currency: Currency = Currency.getInstance("USD")): Money {
            return Money(BigDecimal.valueOf(amount), currency)
        }
    }
}
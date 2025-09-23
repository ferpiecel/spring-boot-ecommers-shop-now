package com.shopnow.domain.entities

import com.shopnow.domain.valueobjects.CouponId
import com.shopnow.domain.valueobjects.Money
import java.math.BigDecimal
import java.time.LocalDate

/**
 * Coupon domain entity
 */
data class Coupon(
    val id: CouponId,
    val code: String,
    val discountPercentage: BigDecimal,
    val expirationDate: LocalDate,
    val minPurchaseAmount: Money
) {
    init {
        require(code.isNotBlank()) { "Coupon code cannot be blank" }
        require(discountPercentage > BigDecimal.ZERO && discountPercentage <= BigDecimal(100)) {
            "Discount percentage must be between 0 and 100"
        }
    }

    val isExpired: Boolean
        get() = LocalDate.now().isAfter(expirationDate)

    fun isValidForAmount(amount: Money): Boolean {
        return !isExpired && amount.amount >= minPurchaseAmount.amount
    }

    fun calculateDiscount(amount: Money): Money {
        if (!isValidForAmount(amount)) {
            return Money.ZERO
        }
        return amount * (discountPercentage.divide(BigDecimal(100)))
    }

    companion object {
        fun create(
            code: String,
            discountPercentage: BigDecimal,
            expirationDate: LocalDate,
            minPurchaseAmount: Money
        ): Coupon {
            return Coupon(
                id = CouponId.generate(),
                code = code,
                discountPercentage = discountPercentage,
                expirationDate = expirationDate,
                minPurchaseAmount = minPurchaseAmount
            )
        }
    }
}
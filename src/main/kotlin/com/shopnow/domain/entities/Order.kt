package com.shopnow.domain.entities

import com.shopnow.domain.valueobjects.*
import java.time.LocalDateTime

/**
 * Order domain entity - Aggregate Root
 */
data class Order(
    val id: OrderId,
    val userId: UserId,
    val orderDate: LocalDateTime,
    val status: OrderStatus,
    val items: List<OrderItem>,
    val shippingAddressId: AddressId,
    val billingAddressId: AddressId
) {
    init {
        require(items.isNotEmpty()) { "Order must have at least one item" }
    }

    val totalAmount: Money
        get() = items.fold(Money.ZERO) { acc, item -> acc + item.totalPrice }

    fun updateStatus(newStatus: OrderStatus): Order {
        return copy(status = newStatus)
    }

    fun canBeCancelled(): Boolean {
        return status in listOf(OrderStatus.PENDING, OrderStatus.CONFIRMED)
    }

    fun cancel(): Order {
        require(canBeCancelled()) { "Order cannot be cancelled in current status: $status" }
        return copy(status = OrderStatus.CANCELLED)
    }

    companion object {
        fun create(
            userId: UserId,
            items: List<OrderItem>,
            shippingAddressId: AddressId,
            billingAddressId: AddressId
        ): Order {
            return Order(
                id = OrderId.generate(),
                userId = userId,
                orderDate = LocalDateTime.now(),
                status = OrderStatus.PENDING,
                items = items,
                shippingAddressId = shippingAddressId,
                billingAddressId = billingAddressId
            )
        }
    }
}

/**
 * Order Item entity
 */
data class OrderItem(
    val id: String,
    val orderId: OrderId,
    val productId: ProductId,
    val quantity: Quantity,
    val unitPrice: Money
) {
    val totalPrice: Money
        get() = unitPrice * quantity.value

    companion object {
        fun create(
            productId: ProductId,
            quantity: Quantity,
            unitPrice: Money
        ): OrderItem {
            return OrderItem(
                id = java.util.UUID.randomUUID().toString(),
                orderId = OrderId.from(""), // Will be set when added to order
                productId = productId,
                quantity = quantity,
                unitPrice = unitPrice
            )
        }
    }
}
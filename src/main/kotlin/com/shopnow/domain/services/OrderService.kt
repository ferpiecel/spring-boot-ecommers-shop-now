package com.shopnow.domain.services

import com.shopnow.domain.entities.Cart
import com.shopnow.domain.entities.Order
import com.shopnow.domain.entities.OrderItem
import com.shopnow.domain.repositories.ProductRepository
import com.shopnow.domain.valueobjects.AddressId
import com.shopnow.domain.valueobjects.UserId

/**
 * Order domain service - Contains business logic that spans multiple aggregates
 */
class OrderService(
    private val productRepository: ProductRepository
) {
    
    /**
     * Creates an order from a shopping cart, validating stock availability
     */
    suspend fun createOrderFromCart(
        cart: Cart,
        shippingAddressId: AddressId,
        billingAddressId: AddressId
    ): Result<Order> {
        if (cart.isEmpty) {
            return Result.failure(IllegalArgumentException("Cannot create order from empty cart"))
        }

        // Validate stock availability and create order items
        val orderItems = mutableListOf<OrderItem>()
        
        for (cartItem in cart.items) {
            val product = productRepository.findById(cartItem.productId)
                ?: return Result.failure(IllegalArgumentException("Product not found: ${cartItem.productId}"))
            
            if (product.stockQuantity.value < cartItem.quantity.value) {
                return Result.failure(IllegalArgumentException(
                    "Insufficient stock for product ${product.name}. " +
                    "Available: ${product.stockQuantity.value}, Requested: ${cartItem.quantity.value}"
                ))
            }
            
            val orderItem = OrderItem.create(
                productId = cartItem.productId,
                quantity = cartItem.quantity,
                unitPrice = cartItem.unitPrice
            )
            orderItems.add(orderItem)
        }

        val order = Order.create(
            userId = cart.userId,
            items = orderItems,
            shippingAddressId = shippingAddressId,
            billingAddressId = billingAddressId
        )

        return Result.success(order)
    }

    /**
     * Validates if an order can be created based on current product availability
     */
    suspend fun validateOrderAvailability(cart: Cart): Result<Unit> {
        for (cartItem in cart.items) {
            val product = productRepository.findById(cartItem.productId)
                ?: return Result.failure(IllegalArgumentException("Product not found: ${cartItem.productId}"))
            
            if (product.stockQuantity.value < cartItem.quantity.value) {
                return Result.failure(IllegalArgumentException(
                    "Insufficient stock for product ${product.name}"
                ))
            }
        }
        
        return Result.success(Unit)
    }
}
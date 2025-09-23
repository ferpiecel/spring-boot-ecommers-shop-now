package com.shopnow.domain.valueobjects

import java.util.*

/**
 * Generic Id value object for type-safe identifiers
 */
@JvmInline
value class Id<T>(val value: String) {
    init {
        require(value.isNotBlank()) { "Id cannot be blank" }
    }
    
    companion object {
        fun <T> generate(): Id<T> = Id(UUID.randomUUID().toString())
        fun <T> from(value: String): Id<T> = Id(value)
    }
}

// Specific Id types for type safety
typealias UserId = Id<User>
typealias ProductId = Id<Product> 
typealias OrderId = Id<Order>
typealias CategoryId = Id<Category>
typealias CartId = Id<Cart>
typealias AddressId = Id<Address>
typealias PaymentMethodId = Id<PaymentMethod>
typealias ReviewId = Id<Review>
typealias WishlistId = Id<Wishlist>
typealias InventoryId = Id<Inventory>
typealias SupplierId = Id<Supplier>
typealias CouponId = Id<Coupon>
typealias DiscountId = Id<Discount>
typealias TaxId = Id<Tax>
typealias ShippingMethodId = Id<ShippingMethod>

// Marker classes for type safety
class User
class Product
class Order  
class Category
class Cart
class Address
class PaymentMethod
class Review
class Wishlist
class Inventory
class Supplier
class Coupon
class Discount
class Tax
class ShippingMethod
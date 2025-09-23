package com.shopnow.domain.valueobjects

/**
 * Password value object - stores hashed password
 */
@JvmInline
value class Password(val hashedValue: String) {
    init {
        require(hashedValue.isNotBlank()) { "Password hash cannot be blank" }
    }

    companion object {
        fun fromHash(hashedPassword: String): Password {
            return Password(hashedPassword)
        }
    }
}
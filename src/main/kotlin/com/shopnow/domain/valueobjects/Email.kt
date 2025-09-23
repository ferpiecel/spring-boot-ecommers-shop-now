package com.shopnow.domain.valueobjects

/**
 * Email value object with validation
 */
@JvmInline
value class Email(val value: String) {
    init {
        require(value.isNotBlank()) { "Email cannot be blank" }
        require(isValidEmail(value)) { "Invalid email format: $value" }
    }

    companion object {
        private val EMAIL_PATTERN = Regex(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        )

        private fun isValidEmail(email: String): Boolean {
            return EMAIL_PATTERN.matches(email)
        }
    }
}
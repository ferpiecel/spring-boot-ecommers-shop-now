package com.shopnow.domain.entities

import com.shopnow.domain.valueobjects.ProductId
import com.shopnow.domain.valueobjects.ReviewId
import com.shopnow.domain.valueobjects.UserId
import java.time.LocalDateTime

/**
 * Review domain entity
 */
data class Review(
    val id: ReviewId,
    val productId: ProductId,
    val userId: UserId,
    val rating: Int,
    val reviewText: String?,
    val reviewDate: LocalDateTime
) {
    init {
        require(rating in 1..5) { "Rating must be between 1 and 5" }
    }

    companion object {
        fun create(
            productId: ProductId,
            userId: UserId,
            rating: Int,
            reviewText: String?
        ): Review {
            return Review(
                id = ReviewId.generate(),
                productId = productId,
                userId = userId,
                rating = rating,
                reviewText = reviewText,
                reviewDate = LocalDateTime.now()
            )
        }
    }
}
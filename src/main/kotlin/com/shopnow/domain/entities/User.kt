package com.shopnow.domain.entities

import com.shopnow.domain.valueobjects.*
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * User domain entity - Aggregate Root
 */
data class User(
    val id: UserId,
    val username: String,
    val password: Password,
    val email: Email,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate? = null,
    val registrationDate: LocalDateTime,
    val lastLoginDate: LocalDateTime? = null
) {
    init {
        require(username.isNotBlank()) { "Username cannot be blank" }
        require(firstName.isNotBlank()) { "First name cannot be blank" }
        require(lastName.isNotBlank()) { "Last name cannot be blank" }
    }

    val fullName: String
        get() = "$firstName $lastName"

    fun updateLastLogin(loginTime: LocalDateTime): User {
        return copy(lastLoginDate = loginTime)
    }

    companion object {
        fun create(
            username: String,
            password: Password,
            email: Email,
            firstName: String,
            lastName: String,
            dateOfBirth: LocalDate? = null
        ): User {
            return User(
                id = UserId.generate(),
                username = username,
                password = password,
                email = email,
                firstName = firstName,
                lastName = lastName,
                dateOfBirth = dateOfBirth,
                registrationDate = LocalDateTime.now()
            )
        }
    }
}
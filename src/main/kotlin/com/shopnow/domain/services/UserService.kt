package com.shopnow.domain.services

import com.shopnow.domain.entities.User
import com.shopnow.domain.repositories.UserRepository
import com.shopnow.domain.valueobjects.Email
import com.shopnow.domain.valueobjects.Password
import java.time.LocalDate

/**
 * User domain service - Contains business logic for user management
 */
class UserService(
    private val userRepository: UserRepository
) {

    /**
     * Registers a new user, ensuring username and email are unique
     */
    suspend fun registerUser(
        username: String,
        password: Password,
        email: Email,
        firstName: String,
        lastName: String,
        dateOfBirth: LocalDate? = null
    ): Result<User> {
        
        // Check if username already exists
        if (userRepository.existsByUsername(username)) {
            return Result.failure(IllegalArgumentException("Username already exists: $username"))
        }
        
        // Check if email already exists
        if (userRepository.existsByEmail(email)) {
            return Result.failure(IllegalArgumentException("Email already exists: ${email.value}"))
        }
        
        val user = User.create(
            username = username,
            password = password,
            email = email,
            firstName = firstName,
            lastName = lastName,
            dateOfBirth = dateOfBirth
        )
        
        return Result.success(user)
    }

    /**
     * Validates user credentials for login
     */
    suspend fun validateUserCredentials(username: String, password: String): Result<User> {
        val user = userRepository.findByUsername(username)
            ?: return Result.failure(IllegalArgumentException("Invalid username or password"))
        
        // In a real implementation, you would hash the password and compare
        // For now, we'll just return the user as found
        return Result.success(user)
    }
}
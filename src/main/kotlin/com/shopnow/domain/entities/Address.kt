package com.shopnow.domain.entities

import com.shopnow.domain.valueobjects.AddressId
import com.shopnow.domain.valueobjects.UserId

/**
 * Address domain entity
 */
data class Address(
    val id: AddressId,
    val userId: UserId,
    val streetAddress: String,
    val city: String,
    val stateProvince: String,
    val postalCode: String,
    val country: String
) {
    init {
        require(streetAddress.isNotBlank()) { "Street address cannot be blank" }
        require(city.isNotBlank()) { "City cannot be blank" }
        require(stateProvince.isNotBlank()) { "State/Province cannot be blank" }
        require(postalCode.isNotBlank()) { "Postal code cannot be blank" }
        require(country.isNotBlank()) { "Country cannot be blank" }
    }

    val fullAddress: String
        get() = "$streetAddress, $city, $stateProvince $postalCode, $country"

    companion object {
        fun create(
            userId: UserId,
            streetAddress: String,
            city: String,
            stateProvince: String,
            postalCode: String,
            country: String
        ): Address {
            return Address(
                id = AddressId.generate(),
                userId = userId,
                streetAddress = streetAddress,
                city = city,
                stateProvince = stateProvince,
                postalCode = postalCode,
                country = country
            )
        }
    }
}
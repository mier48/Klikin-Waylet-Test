package com.albertomier.klikinwaylettest.domain.model

import android.os.Parcelable
import com.albertomier.klikinwaylettest.data.database.entities.ShopEntity
import com.albertomier.klikinwaylettest.data.model.AddressResponse
import com.albertomier.klikinwaylettest.data.model.ShopResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val street: String,
    val country: String,
    val city: String,
    val zip: String?
): Parcelable

fun AddressResponse.toDomain() = Address(street, country, city, zip)
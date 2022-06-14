package com.albertomier.klikinwaylettest.domain.model

import android.os.Parcelable
import com.albertomier.klikinwaylettest.data.database.entities.ShopEntity
import com.albertomier.klikinwaylettest.data.model.ShopResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shop(
    var id: String,
    var name: String,
    var address: Address,
    var shortDescription: String?,
    var description: String?,
    var photos: List<Photo>,
    var category: String?,
    var latitude: Double,
    var longitude: Double,
    var distance: Double = 0.0
) : Parcelable, Comparable<Shop> {
    override fun compareTo(other: Shop) = if (this.distance > other.distance) {
        1
    } else {
        -1
    }
}

fun ShopResponse.toDomain() =
    Shop(
        id,
        name,
        address.toDomain(),
        shortDescription,
        description,
        photos.map { it.toDomain() },
        category,
        latitude,
        longitude
    )

fun ShopEntity.toDomain() =
    Shop(id, name, address, shortDescription, description, photos, category, latitude, longitude)
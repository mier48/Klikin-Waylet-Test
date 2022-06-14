package com.albertomier.klikinwaylettest.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albertomier.klikinwaylettest.domain.model.Address
import com.albertomier.klikinwaylettest.domain.model.Photo
import com.albertomier.klikinwaylettest.domain.model.Shop

@Entity(tableName = "shops")
data class ShopEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "address") var address: Address,
    @ColumnInfo(name = "shortDescription") var shortDescription: String?,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "photos") var photos: List<Photo>,
    @ColumnInfo(name = "category") var category: String?,
    @ColumnInfo(name = "latitude") var latitude: Double,
    @ColumnInfo(name = "longitude") var longitude: Double
)

fun Shop.toDatabase() = ShopEntity(id, name, address, shortDescription, description, photos, category, latitude, longitude)
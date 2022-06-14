package com.albertomier.klikinwaylettest.data.database

import androidx.room.TypeConverter
import com.albertomier.klikinwaylettest.domain.model.Address
import com.albertomier.klikinwaylettest.domain.model.Photo
import com.google.gson.Gson
import java.util.*

class Converters {

    @TypeConverter
    fun photosToJson(photos: List<Photo>): String {
        return Gson().toJson(photos)
    }

    @TypeConverter
    fun jsonToPhotos(photos: String): List<Photo> {
        if (photos.isEmpty()) {
            return emptyList()
        }

        return Gson().fromJson(photos, Array<Photo>::class.java).toList()
    }

    @TypeConverter
    fun addressToJson(data: Address): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun jsonToAddress(data: String): Address? {
        if (data.isEmpty()) {
            return null
        }

        return Gson().fromJson(data, Address::class.java)
    }
}
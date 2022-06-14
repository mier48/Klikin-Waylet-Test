package com.albertomier.klikinwaylettest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.albertomier.klikinwaylettest.data.database.dao.ShopDao
import com.albertomier.klikinwaylettest.data.database.entities.ShopEntity

@Database(entities = [ShopEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun getShopDao(): ShopDao
}
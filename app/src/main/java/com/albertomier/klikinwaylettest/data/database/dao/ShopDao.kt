package com.albertomier.klikinwaylettest.data.database.dao

import androidx.room.*
import com.albertomier.klikinwaylettest.data.database.entities.ShopEntity

@Dao
interface ShopDao {

    @Query("SELECT * FROM shops ORDER BY name ASC")
    suspend fun getAllShops(): List<ShopEntity>

    @Insert
    suspend fun addAll(shopList: List<ShopEntity>)

    @Update
    suspend fun updateShop(shop: ShopEntity)

    @Delete
    suspend fun deleteShop(shop: ShopEntity)
}
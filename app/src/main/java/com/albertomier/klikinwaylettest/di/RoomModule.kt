package com.albertomier.klikinwaylettest.di

import android.content.Context
import androidx.room.Room
import com.albertomier.klikinwaylettest.core.SHOP_DATABASE_NAME
import com.albertomier.klikinwaylettest.data.database.ShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ShopDatabase::class.java, SHOP_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideShopDao(db: ShopDatabase) = db.getShopDao()
}
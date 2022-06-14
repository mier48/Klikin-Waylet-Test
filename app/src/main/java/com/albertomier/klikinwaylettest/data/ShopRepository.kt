package com.albertomier.klikinwaylettest.data

import com.albertomier.klikinwaylettest.KlikinWayletTestApp
import com.albertomier.klikinwaylettest.core.LATITUDE_KEY
import com.albertomier.klikinwaylettest.core.LONGITUDE_KEY
import com.albertomier.klikinwaylettest.core.Utils
import com.albertomier.klikinwaylettest.data.network.ApiResponseStatus
import com.albertomier.klikinwaylettest.data.network.ShopService
import com.albertomier.klikinwaylettest.data.network.makeNetworkCall
import com.albertomier.klikinwaylettest.domain.model.Shop
import com.albertomier.klikinwaylettest.domain.model.toDomain
import javax.inject.Inject

class ShopRepository @Inject constructor(
    private val service: ShopService
) {

    suspend fun getAllShops(): ApiResponseStatus<List<Shop>> =
        makeNetworkCall {
            val shopList = service.getAllShops()
            val result: MutableList<Shop> = mutableListOf()

            val latitude = KlikinWayletTestApp.preference.getLong(LATITUDE_KEY).toDouble()
            val longitude = KlikinWayletTestApp.preference.getLong(LONGITUDE_KEY).toDouble()

            shopList.map {
                val shop = it.toDomain()
                if (latitude > -99 && longitude > -99) {
                    shop.distance =
                        Utils.distanceInKm(latitude, longitude, shop.latitude, shop.longitude)
                }

                if (it.category == null) {
                    it.category = "OTHER"
                }
                result.add(shop)
            }

            if (latitude > -99 && longitude > -99) {
                result.sorted()
            } else {
                result
            }
        }

    suspend fun getCategoryList(): ApiResponseStatus<List<String>> =
        makeNetworkCall {
            val shopList = service.getAllShops()
            val result: MutableList<String> = mutableListOf()

            shopList.map {
                if (it.category != null) {
                    if (!result.contains(it.category)) {
                        result.add(it.category!!)
                    }
                }
            }

            result.sorted()
        }


    suspend fun getFilteredShops(category: String): ApiResponseStatus<List<Shop>> =
        makeNetworkCall {
            val shopList = service.getAllShops()
            val result: MutableList<Shop> = mutableListOf()

            val latitude = KlikinWayletTestApp.preference.getLong(LATITUDE_KEY).toDouble()
            val longitude = KlikinWayletTestApp.preference.getLong(LONGITUDE_KEY).toDouble()

            shopList.map {
                val shop = it.toDomain()
                if (latitude > -99 && longitude > -99) {
                    shop.distance =
                        Utils.distanceInKm(latitude, longitude, shop.latitude, shop.longitude)
                }

                if (it.category != null && it.category == category) {
                    result.add(shop)
                }
            }

            if (latitude > -99 && longitude > -99) {
                result.sorted()
            } else {
                result
            }
        }
}
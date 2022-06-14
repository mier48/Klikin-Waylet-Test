package com.albertomier.klikinwaylettest.domain

import com.albertomier.klikinwaylettest.data.ShopRepository
import com.albertomier.klikinwaylettest.data.network.ApiResponseStatus
import com.albertomier.klikinwaylettest.domain.model.Shop
import javax.inject.Inject

class GetFilteredShopList @Inject constructor(private val repository: ShopRepository) {
    suspend operator fun invoke(filter: String): ApiResponseStatus<List<Shop>> =
        repository.getFilteredShops(filter)
}
package com.albertomier.klikinwaylettest.data.network

import com.albertomier.klikinwaylettest.data.model.ApiResponse
import com.albertomier.klikinwaylettest.data.model.ShopResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ShopService @Inject constructor(private val api: ApiClient) {

    suspend fun getAllShops(): List<ShopResponse> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<ShopResponse>> = api.getAllShops()
            response.body() ?: emptyList()
        }
    }
}
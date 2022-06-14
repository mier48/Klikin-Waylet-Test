package com.albertomier.klikinwaylettest.data.network

import com.albertomier.klikinwaylettest.data.model.ApiResponse
import com.albertomier.klikinwaylettest.data.model.ShopResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("public")
    suspend fun getAllShops(): Response<List<ShopResponse>>
}
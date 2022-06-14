package com.albertomier.klikinwaylettest.data.model

import com.google.gson.annotations.SerializedName

data class ShopResponse(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("address") var address: AddressResponse,
    @SerializedName("shortDescription") var shortDescription: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("photos") var photos: List<PhotoResponse>,
    @SerializedName("category") var category: String?,
    @SerializedName("latitude") var latitude: Double,
    @SerializedName("longitude") var longitude: Double
)
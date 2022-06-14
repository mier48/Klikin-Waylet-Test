package com.albertomier.klikinwaylettest.data.model

import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("street") val street: String,
    @SerializedName("country") val country: String,
    @SerializedName("city") val city: String,
    @SerializedName("zip") val zip: String?
)
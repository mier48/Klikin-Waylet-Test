package com.albertomier.klikinwaylettest.data.model

import com.google.gson.annotations.SerializedName

data class ThumbnailResponse(
    @SerializedName("small") var small: String,
    @SerializedName("medium") var medium: String,
    @SerializedName("large") var large: String,
)
package com.albertomier.klikinwaylettest.data.model

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("_id") var id: String,
    @SerializedName("url") var url: String,
    @SerializedName("thumbnails") var thumbnails: ThumbnailResponse,
)
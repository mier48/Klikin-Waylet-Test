package com.albertomier.klikinwaylettest.domain.model

import android.os.Parcelable
import com.albertomier.klikinwaylettest.data.model.PhotoResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val url: String,
    var thumbnail: Thumbnail
) : Parcelable

fun PhotoResponse.toDomain() = Photo(id, url, thumbnails.toDomain())
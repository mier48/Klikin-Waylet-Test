package com.albertomier.klikinwaylettest.domain.model

import android.os.Parcelable
import com.albertomier.klikinwaylettest.data.model.ThumbnailResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    val small: String,
    val medium: String,
    val large: String
) : Parcelable

fun ThumbnailResponse.toDomain() = Thumbnail(small, medium, large)
package com.binhth.photocollection.data.remote.response

import com.binhth.photocollection.data.model.PhotoEntity
import com.google.gson.annotations.SerializedName

open class SearchPhotoResponse(
    @SerializedName("total") val total: Int? = null,
    @SerializedName("total_pages") val totalPage: Int? = null,
    @SerializedName("results") val listPhotos: List<PhotoEntity>
)

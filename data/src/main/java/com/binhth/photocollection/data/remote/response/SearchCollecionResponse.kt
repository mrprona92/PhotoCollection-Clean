package com.binhth.photocollection.data.remote.response

import com.binhth.photocollection.data.model.CollectionEntity
import com.google.gson.annotations.SerializedName

open class SearchCollecionResponse(
    @SerializedName("total") val total: Int? = null,
    @SerializedName("total_pages") val totalPage: Int? = null,
    @SerializedName("results") val listCollecion: List<CollectionEntity>
)

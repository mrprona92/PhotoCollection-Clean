package com.binhth.photocollection.domain.model

import com.binhth.photocollection.domain.base.BaseModel

data class Collection(
    val id: Int? = null,
    val title: String? = null,
    val totalPhotos: Int? = null,
    val coverPhoto: CoverPhoto? = null
) : BaseModel()


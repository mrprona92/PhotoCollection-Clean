package com.binhth.photocollection.domain.model

import com.binhth.photocollection.domain.base.BaseModel

data class Photo(
    val id: String? = null,
    val createdAt: String? = null,
    val urls: Urls? = null,
    val user: User? = null,
    val likes: Int? = null
) : BaseModel()

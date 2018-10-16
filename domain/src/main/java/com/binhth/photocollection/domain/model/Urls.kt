package com.binhth.photocollection.domain.model

import com.binhth.photocollection.domain.base.BaseModel

data class Urls(
    val small: String? = null,
    val thumb: String? = null,
    val raw: String? = null,
    val regular: String? = null,
    val full: String? = null
) : BaseModel()

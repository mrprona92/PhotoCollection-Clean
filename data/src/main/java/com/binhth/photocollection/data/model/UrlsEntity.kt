package com.binhth.photocollection.data.model

import com.binhth.photocollection.data.base.BaseEntity

data class UrlsEntity(
    val small: String? = null,
    val thumb: String? = null,
    val raw: String? = null,
    val regular: String? = null,
    val full: String? = null
) : BaseEntity()

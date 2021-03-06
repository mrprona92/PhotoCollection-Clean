package com.binhth.photocollection.domain.model

import com.binhth.photocollection.domain.base.BaseModel

data class User(
    val id: String? = null,
    val username: String? = null
) : BaseModel()

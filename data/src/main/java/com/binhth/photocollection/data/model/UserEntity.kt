package com.binhth.photocollection.data.model

import com.binhth.photocollection.data.base.BaseEntity

data class UserEntity(
    val id: String? = null,
    val username: String? = null
) : BaseEntity()

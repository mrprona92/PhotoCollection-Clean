package com.binhth.photocollection.domain.model

import com.binhth.photocollection.domain.base.BaseModel

data class ProfileImage(
	val small: String? = null,
	val large: String? = null,
	val medium: String? = null
): BaseModel()

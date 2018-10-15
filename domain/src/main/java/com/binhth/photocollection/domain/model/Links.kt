package com.binhth.photocollection.domain.model

import com.binhth.photocollection.domain.base.BaseModel

data class Links(
	val followers: String? = null,
	val portfolio: String? = null,
	val following: String? = null,
	val self: String? = null,
	val html: String? = null,
	val photos: String? = null,
	val likes: String? = null,
	val related: String? = null,
	val download: String? = null,
	val downloadLocation: String? = null
): BaseModel()

package com.binhth.photocollection.domain.model

import com.binhth.photocollection.domain.base.BaseModel

data class PreviewPhotosItem(
	val urls: Urls? = null,
	val id: Int? = null
): BaseModel()

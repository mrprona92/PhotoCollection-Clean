package com.binhth.photocollection.domain.model

import com.binhth.photocollection.domain.base.BaseModel

data class CoverPhoto(
	val currentUserCollections: List<Any?>? = null,
	val color: String? = null,
	val createdAt: String? = null,
	val description: Any? = null,
	val sponsored: Boolean? = null,
	val likedByUser: Boolean? = null,
	val urls: Urls? = null,
	val updatedAt: String? = null,
	val width: Int? = null,
	val links: Links? = null,
	val id: String? = null,
	val categories: List<Any?>? = null,
	val user: User? = null,
	val slug: Any? = null,
	val height: Int? = null,
	val likes: Int? = null
): BaseModel()

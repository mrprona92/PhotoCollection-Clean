package com.binhth.photocollection.domain.model

import com.binhth.photocollection.domain.base.BaseModel

data class Collection(
    val featured: Boolean? = null,
    val jsonMemberPrivate: Boolean? = null,
    val coverPhoto: CoverPhoto? = null,
    val totalPhotos: Int? = null,
    val shareKey: String? = null,
    val description: Any? = null,
    val title: String? = null,
    val tags: List<TagsItem?>? = null,
    val previewPhotos: List<PreviewPhotosItem?>? = null,
    val updatedAt: String? = null,
    val curated: Boolean? = null,
    val links: Links? = null,
    val id: Int? = null,
    val publishedAt: String? = null,
    val user: User? = null
) : BaseModel()


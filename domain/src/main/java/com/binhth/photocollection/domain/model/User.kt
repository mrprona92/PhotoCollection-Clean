package com.binhth.photocollection.domain.model

import com.binhth.photocollection.domain.base.BaseModel

data class User(
    val totalPhotos: Int? = null,
    val acceptedTos: Boolean? = null,
    val twitterUsername: String? = null,
    val lastName: String? = null,
    val bio: Any? = null,
    val totalLikes: Int? = null,
    val portfolioUrl: String? = null,
    val profileImage: ProfileImage? = null,
    val updatedAt: String? = null,
    val name: String? = null,
    val location: Any? = null,
    val links: Links? = null,
    val totalCollections: Int? = null,
    val id: String? = null,
    val firstName: String? = null,
    val instagramUsername: String? = null,
    val username: String? = null
) : BaseModel()

package com.binhth.photocollection.model

import com.binhth.photocollection.base.BaseItem
import com.binhth.photocollection.base.ItemMapper
import com.binhth.photocollection.domain.model.Photo
import com.binhth.photocollection.domain.model.Urls
import com.binhth.photocollection.domain.model.User

data class PhotoItem(
    val id: String?,
    val createdAt: String? = null,
    val urls: String? = null,
    val username: String? = null,
    val likes: Int? = null
) : BaseItem()

class PhotoItemMapper : ItemMapper<Photo, PhotoItem> {
    override fun mapToPresentation(model: Photo) = PhotoItem(
        id = model.id,
        createdAt = model.createdAt,
        urls = model.urls?.full,
        username = model.user?.username,
        likes = model.likes
    )
    override fun mapToDomain(modelItem: PhotoItem) = Photo(
        id = modelItem.id,
        createdAt = modelItem.createdAt,
        urls = Urls(full = modelItem.urls),
        user = User(username = modelItem.username),
        likes = modelItem.likes
    )
}
package com.binhth.photocollection.model

import com.binhth.photocollection.base.BaseItem
import com.binhth.photocollection.base.ItemMapper
import com.binhth.photocollection.domain.model.Collection
import com.binhth.photocollection.domain.model.CoverPhoto
import com.binhth.photocollection.domain.model.Urls

data class CollectionItem(
    val id: String,
    val title: String? = null,
    val totalPhotos: String? = null,
    val coverPhoto: String? = null
) : BaseItem()

class CollectionItemMapper : ItemMapper<Collection, CollectionItem> {
    override fun mapToPresentation(model: Collection) = CollectionItem(
        id = model.id.toString(),
        title = model.title,
        totalPhotos = model.totalPhotos.toString(),
        coverPhoto = model.coverPhoto?.urls?.full
    )

    override fun mapToDomain(modelItem: CollectionItem) = Collection(
        id = modelItem.id.toInt(),
        title = modelItem.title,
        totalPhotos = modelItem.totalPhotos?.toInt(),
        coverPhoto = CoverPhoto(Urls(full = modelItem.coverPhoto))
    )
}

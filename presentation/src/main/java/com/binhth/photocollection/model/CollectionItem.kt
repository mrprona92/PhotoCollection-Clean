package com.binhth.photocollection.model

import com.binhth.photocollection.base.BaseItem
import com.binhth.photocollection.base.ItemMapper
import com.binhth.photocollection.domain.model.Collection

data class CollectionItem(
    val id: String,
    val title: String? = null,
    val user: String? = null,
    val coverimage: String? = null,
    val publishedAt: String? = null
) : BaseItem()


class CollectionItemMapper : ItemMapper<Collection, CollectionItem> {

    override fun mapToPresentation(model: Collection) = CollectionItem(
        id = model.id.toString(),
        title = model.title,
        coverimage = model.coverPhoto?.urls?.full,
        publishedAt = model.publishedAt,
        user = model.user?.username
    )

    override fun mapToDomain(modelItem: CollectionItem) = Collection(
        id = modelItem.id.toInt(),
        title = modelItem.title,
        publishedAt = modelItem.publishedAt
    )
}
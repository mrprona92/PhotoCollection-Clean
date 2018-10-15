package com.binhth.photocollection.data.model

import androidx.room.Entity
import com.binhth.photocollection.data.base.BaseEntity
import com.binhth.photocollection.data.base.EntityMapper
import com.binhth.photocollection.domain.model.Collection
import com.google.gson.annotations.SerializedName

@Entity(tableName = "collection", primaryKeys = ["id"])
data class CollectionEntity(
    @field: SerializedName("id") val id: Int?,
    @field: SerializedName("title") val title: String? = null,
    @field: SerializedName("coverimage") val coverimage: String? = null,
    @field: SerializedName("publishedAt") val publishedAt: String? = null,
    @field: SerializedName("user") val user: String? = null
) : BaseEntity()


class CollectionEntityMapper : EntityMapper<Collection, CollectionEntity> {

    override fun mapToDomain(entity: CollectionEntity) = Collection(
        id = entity.id,
        title = entity.title,
        publishedAt = entity.publishedAt
    )

    override fun mapToEntity(model: Collection) = CollectionEntity(
        id = model.id,
        title = model.title,
        coverimage = model.coverPhoto?.urls?.full,
        publishedAt = model.publishedAt,
        user = model.user?.username
    )
}

package com.binhth.photocollection.data.model

import androidx.room.Entity
import com.binhth.photocollection.data.base.BaseEntity
import com.binhth.photocollection.data.base.EntityMapper
import com.binhth.photocollection.domain.model.Collection
import com.binhth.photocollection.domain.model.CoverPhoto
import com.google.gson.annotations.SerializedName

@Entity(tableName = "collection", primaryKeys = ["id"])
data class CollectionEntity(
    @field: SerializedName("id") val id: Int?,
    @field: SerializedName("title") val title: String? = null,
    @field: SerializedName("total_photos") val totalPhotos: Int? = null,
    @field: SerializedName("cover_photo") val coverPhoto: CoverPhoto? = null
) : BaseEntity()


class CollectionEntityMapper : EntityMapper<Collection, CollectionEntity> {

    override fun mapToDomain(entity: CollectionEntity) = Collection(
        id = entity.id,
        title = entity.title,
        totalPhotos = entity.totalPhotos,
        coverPhoto = entity.coverPhoto
    )

    override fun mapToEntity(model: Collection) = CollectionEntity(
        id = model.id,
        title = model.title,
        totalPhotos = model.totalPhotos,
        coverPhoto = model.coverPhoto
    )
}

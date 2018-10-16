package com.binhth.photocollection.data.model

import androidx.room.Entity
import com.binhth.photocollection.data.base.BaseEntity
import com.binhth.photocollection.data.base.EntityMapper
import com.binhth.photocollection.domain.model.Collection
import com.binhth.photocollection.domain.model.CoverPhoto
import com.binhth.photocollection.domain.model.Urls
import com.google.gson.annotations.SerializedName


@Entity(tableName = "collection", primaryKeys = ["id"])
data class CollectionRoomEntity(
    @field: SerializedName("id") val id: Int?,
    @field: SerializedName("title") val title: String? = null,
    @field: SerializedName("total_photos") val totalPhotos: Int? = null,
    @field: SerializedName("cover_photo") val coverPhoto: String? = null
) : BaseEntity()


class CollectionRoomEntityMapper : EntityMapper<Collection, CollectionRoomEntity> {
    override fun mapToDomain(entity: CollectionRoomEntity) = Collection(
        id = entity.id,
        title = entity.title,
        totalPhotos = entity.totalPhotos,
        coverPhoto = CoverPhoto(
            Urls(regular = entity.coverPhoto)
        )
    )

    override fun mapToEntity(model: Collection) = CollectionRoomEntity(
        id = model.id,
        title = model.title,
        totalPhotos = model.totalPhotos,
        coverPhoto = model.coverPhoto?.urls?.regular
    )
}

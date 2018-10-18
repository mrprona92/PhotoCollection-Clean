package com.binhth.photocollection.data.model

import androidx.room.Entity
import com.binhth.photocollection.data.base.BaseEntity
import com.binhth.photocollection.data.base.EntityMapper
import com.binhth.photocollection.domain.model.Collection


@Entity(tableName = "collection", primaryKeys = ["id"])
data class CollectionRoomEntity(
    val id: Int?,
    val title: String? = null,
    val totalPhotos: Int? = null
) : BaseEntity()


class CollectionRoomEntityMapper : EntityMapper<Collection, CollectionRoomEntity> {
    override fun mapToDomain(entity: CollectionRoomEntity) = Collection(
        id = entity.id,
        title = entity.title,
        totalPhotos = entity.totalPhotos
    )

    override fun mapToEntity(model: Collection) = CollectionRoomEntity(
        id = model.id,
        title = model.title,
        totalPhotos = model.totalPhotos
    )
}

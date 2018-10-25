package com.binhth.photocollection.data.model

import com.binhth.photocollection.data.base.BaseEntity
import com.binhth.photocollection.data.base.EntityMapper
import com.binhth.photocollection.domain.model.Collection
import com.binhth.photocollection.domain.model.CoverPhoto
import com.google.gson.annotations.SerializedName

data class CollectionEntity(
    @field: SerializedName("id") val id: Int?,
    @field: SerializedName("title") val title: String? = null,
    @field: SerializedName("total_photos") val totalPhotos: Int? = null,
    @field: SerializedName("cover_photo") val coverPhoto: CoverPhotoEntity? = null
) : BaseEntity()


class CollectionEntityMapper(
    private val coverPhotoEntityMapper: CoverPhotoEntityMapper
) : EntityMapper<Collection, CollectionEntity> {

    override fun mapToDomain(entity: CollectionEntity) = Collection(
        id = entity.id,
        title = entity.title,
        totalPhotos = entity.totalPhotos,
        coverPhoto = coverPhotoEntityMapper.mapToDomain(entity.coverPhoto ?: CoverPhotoEntity())
    )

    override fun mapToEntity(model: Collection) = CollectionEntity(
        id = model.id,
        title = model.title,
        totalPhotos = model.totalPhotos,
        coverPhoto = coverPhotoEntityMapper.mapToEntity(model.coverPhoto ?: CoverPhoto())
    )
}

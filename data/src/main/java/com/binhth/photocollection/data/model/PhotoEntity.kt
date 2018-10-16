package com.binhth.photocollection.data.model

import androidx.room.Entity
import com.binhth.photocollection.data.base.BaseEntity
import com.binhth.photocollection.data.base.EntityMapper
import com.binhth.photocollection.domain.model.Photo
import com.binhth.photocollection.domain.model.Urls
import com.binhth.photocollection.domain.model.User
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photo", primaryKeys = ["id"])
data class PhotoEntity(
    @field: SerializedName("id") val id: String?,
    @field: SerializedName("created_at") val createdAt: String? = null,
    @field: SerializedName("urls") val urls: Urls? = null,
    @field: SerializedName("user") val user: User? = null,
    @field: SerializedName("likes") val likes: Int? = null
) : BaseEntity()


class PhotoEntityMapper : EntityMapper<Photo, PhotoEntity> {

    override fun mapToDomain(entity: PhotoEntity) = Photo(
        id = entity.id,
        createdAt = entity.createdAt,
        urls = entity.urls,
        user = entity.user,
        likes = entity.likes
    )

    override fun mapToEntity(model: Photo) = PhotoEntity(
        id = model.id,
        createdAt = model.createdAt,
        urls = model.urls,
        user = model.user,
        likes = model.likes
    )
}
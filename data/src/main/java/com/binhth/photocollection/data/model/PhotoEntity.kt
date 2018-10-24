package com.binhth.photocollection.data.model

import com.binhth.photocollection.data.base.BaseEntity
import com.binhth.photocollection.data.base.EntityMapper
import com.binhth.photocollection.domain.model.Photo
import com.binhth.photocollection.domain.model.Urls
import com.binhth.photocollection.domain.model.User
import com.google.gson.annotations.SerializedName

data class PhotoEntity(
    @field: SerializedName("id") val id: String?,
    @field: SerializedName("created_at") val createdAt: String? = null,
    @field: SerializedName("urls") val urls: UrlsEntity? = null,
    @field: SerializedName("user") val user: UserEntity? = null,
    @field: SerializedName("likes") val likes: Int? = null
) : BaseEntity()


class PhotoEntityMapper : EntityMapper<Photo, PhotoEntity> {

    override fun mapToDomain(entity: PhotoEntity) = Photo(
        id = entity.id,
        createdAt = entity.createdAt,
        urls = Urls(entity.urls?.small, entity.urls?.thumb, entity.urls?.raw, entity.urls?.regular, entity.urls?.full),
        user = User(entity.user?.id, entity.user?.username),
        likes = entity.likes
    )

    override fun mapToEntity(model: Photo) = PhotoEntity(
        id = model.id,
        createdAt = model.createdAt,
        urls = UrlsEntity(model.urls?.small, model.urls?.thumb, model.urls?.raw, model.urls?.regular, model.urls?.full),
        user = UserEntity(model.user?.id, model.user?.username),
        likes = model.likes
    )
}
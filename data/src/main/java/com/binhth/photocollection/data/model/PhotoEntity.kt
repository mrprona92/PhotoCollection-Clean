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

class PhotoEntityMapper(private val urlsEntityMapper: UrlsEntityMapper, private val userEntityMapper: UserEntityMapper) :
    EntityMapper<Photo, PhotoEntity> {

    override fun mapToDomain(entity: PhotoEntity) = Photo(
        id = entity.id,
        createdAt = entity.createdAt,
        urls = urlsEntityMapper.mapToDomain(entity.urls ?: UrlsEntity()),
        user = userEntityMapper.mapToDomain(entity.user ?: UserEntity()),
        likes = entity.likes
    )

    override fun mapToEntity(model: Photo) = PhotoEntity(
        id = model.id,
        createdAt = model.createdAt,
        urls = urlsEntityMapper.mapToEntity(model.urls ?: Urls()),
        user = userEntityMapper.mapToEntity(model.user ?: User()),
        likes = model.likes
    )
}
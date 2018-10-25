package com.binhth.photocollection.data.model

import com.binhth.photocollection.data.base.BaseEntity
import com.binhth.photocollection.data.base.EntityMapper
import com.binhth.photocollection.domain.model.CoverPhoto
import com.binhth.photocollection.domain.model.Urls

data class CoverPhotoEntity(
    val urls: UrlsEntity? = null
) : BaseEntity()

class CoverPhotoEntityMapper(private val urlsEntityMapper: UrlsEntityMapper) : EntityMapper<CoverPhoto, CoverPhotoEntity> {
    override fun mapToDomain(entity: CoverPhotoEntity) = CoverPhoto(
        urls = urlsEntityMapper.mapToDomain(entity.urls ?: UrlsEntity())
    )

    override fun mapToEntity(model: CoverPhoto) = CoverPhotoEntity(
        urls = urlsEntityMapper.mapToEntity(model.urls ?: Urls())
    )
}
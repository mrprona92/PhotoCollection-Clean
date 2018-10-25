package com.binhth.photocollection.data.model

import com.binhth.photocollection.data.base.BaseEntity
import com.binhth.photocollection.data.base.EntityMapper
import com.binhth.photocollection.domain.model.Urls

data class UrlsEntity(
    val small: String? = null,
    val thumb: String? = null,
    val raw: String? = null,
    val regular: String? = null,
    val full: String? = null
) : BaseEntity()

class UrlsEntityMapper : EntityMapper<Urls, UrlsEntity> {
    override fun mapToDomain(entity: UrlsEntity) = Urls(
        small = entity.small,
        thumb = entity.thumb,
        raw = entity.raw,
        regular = entity.regular,
        full = entity.full
    )

    override fun mapToEntity(model: Urls) = UrlsEntity(
        small = model.small,
        thumb = model.thumb,
        raw = model.raw,
        regular = model.regular,
        full = model.full
    )
}
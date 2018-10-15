package com.binhth.photocollection.data.base

import com.binhth.photocollection.domain.base.BaseModel

interface EntityMapper<M : BaseModel, E : BaseEntity> {
    fun mapToDomain(entity: E): M

    fun mapToEntity(model: M): E
}
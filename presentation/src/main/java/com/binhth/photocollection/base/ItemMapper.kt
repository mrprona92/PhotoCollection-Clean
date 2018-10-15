package com.binhth.photocollection.base

import com.binhth.photocollection.domain.base.BaseModel

interface ItemMapper<M : BaseModel, I : BaseItem> {
    fun mapToPresentation(model: M): I

    fun mapToDomain(modelItem: I): M
}

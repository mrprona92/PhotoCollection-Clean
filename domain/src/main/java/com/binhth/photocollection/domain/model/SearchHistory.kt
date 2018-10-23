package com.binhth.photocollection.domain.model

import com.binhth.photocollection.domain.base.BaseModel

data class SearchHistory(
    val query: String = ""
) : BaseModel()

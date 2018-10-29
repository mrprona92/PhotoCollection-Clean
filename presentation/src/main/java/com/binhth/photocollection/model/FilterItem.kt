package com.binhth.photocollection.model

import com.binhth.photocollection.base.BaseItem
import ja.burhanrashid52.photoeditor.PhotoFilter

data class FilterItem(
    val filterTitle: String? = null,
    val filterName: String? = null,
    val filterImage: PhotoFilter
) : BaseItem()


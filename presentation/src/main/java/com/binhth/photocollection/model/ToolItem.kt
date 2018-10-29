package com.binhth.photocollection.model

import com.binhth.photocollection.base.BaseItem
import com.binhth.photocollection.ui.screen.photoeditor.tooledit.ToolType

data class ToolItem(
    val toolName: String,
    val toolIcon: Int,
    val toolType: ToolType
) : BaseItem()


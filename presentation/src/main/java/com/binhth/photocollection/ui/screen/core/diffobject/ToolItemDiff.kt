package com.binhth.photocollection.ui.screen.core.diffobject

import androidx.recyclerview.widget.DiffUtil
import com.binhth.photocollection.model.ToolItem

open class ToolItemDiff : DiffUtil.ItemCallback<ToolItem>() {

    override fun areItemsTheSame(oldItem: ToolItem, newItem: ToolItem): Boolean {
        return oldItem.toolName == newItem.toolName
    }

    override fun areContentsTheSame(oldItem: ToolItem, newItem: ToolItem): Boolean {
        return oldItem == newItem
    }
}
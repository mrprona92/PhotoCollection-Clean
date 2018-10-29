package com.binhth.photocollection.ui.screen.core.diffobject

import androidx.recyclerview.widget.DiffUtil
import com.binhth.photocollection.model.FilterItem

open class FilterDiff : DiffUtil.ItemCallback<FilterItem>() {

    override fun areItemsTheSame(oldItem: FilterItem, newItem: FilterItem): Boolean {
        return oldItem.filterName == newItem.filterName
    }

    override fun areContentsTheSame(oldItem: FilterItem, newItem: FilterItem): Boolean {
        return oldItem == newItem
    }
}
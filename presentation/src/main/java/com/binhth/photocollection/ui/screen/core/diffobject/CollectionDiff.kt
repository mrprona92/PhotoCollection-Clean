package com.binhth.photocollection.ui.screen.core.diffobject

import androidx.recyclerview.widget.DiffUtil
import com.binhth.photocollection.model.CollectionItem

open class CollectionDiff : DiffUtil.ItemCallback<CollectionItem>() {

    override fun areItemsTheSame(oldItem: CollectionItem, newItem: CollectionItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CollectionItem, newItem: CollectionItem): Boolean {
        return oldItem == newItem
    }
}
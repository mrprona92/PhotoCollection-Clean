package com.binhth.photocollection.ui.screen.core.diffobject

import androidx.recyclerview.widget.DiffUtil
import com.binhth.photocollection.model.PhotoItem

open class PhotoDiff : DiffUtil.ItemCallback<PhotoItem>() {
    override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem.urls == newItem.urls && oldItem.username == newItem.username
    }
}
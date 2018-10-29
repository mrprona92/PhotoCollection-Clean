package com.binhth.photocollection.ui.screen.core.diffobject

import androidx.recyclerview.widget.DiffUtil
import com.binhth.photocollection.model.ColorPickerItem

open class ColorPickerItemDiff : DiffUtil.ItemCallback<ColorPickerItem>() {

    override fun areItemsTheSame(oldItem: ColorPickerItem, newItem: ColorPickerItem): Boolean {
        return oldItem.backgroundColor == newItem.backgroundColor
    }

    override fun areContentsTheSame(oldItem: ColorPickerItem, newItem: ColorPickerItem): Boolean {
        return oldItem == newItem
    }
}
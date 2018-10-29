package com.binhth.photocollection.ui.screen.photoeditor.brush.colorpicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.binhth.photocollection.R
import com.binhth.photocollection.databinding.ItemColorPickerBinding
import com.binhth.photocollection.model.ColorPickerItem
import com.binhth.photocollection.ui.screen.core.BaseRecyclerAdapter
import com.binhth.photocollection.ui.screen.core.diffobject.ColorPickerItemDiff


class ColorPickerAdapter(private val itemClick: (ColorPickerItem) -> Unit) :
    BaseRecyclerAdapter<ColorPickerItem>(object :
        ColorPickerItemDiff() {}) {

    override fun bindFirstTime(binding: ViewDataBinding) {
        if (binding is ItemColorPickerBinding) {
            binding.apply {
                root.setOnClickListener {
                    item?.apply {
                        itemClick.invoke(this)
                    }
                }
            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_color_picker, parent, false
        )
    }

    override fun bind(binding: ViewDataBinding, item: ColorPickerItem) {
        if (binding is ItemColorPickerBinding) binding.item = item
    }
}
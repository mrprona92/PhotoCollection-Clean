package com.binhth.photocollection.ui.screen.photoeditor.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.binhth.photocollection.R
import com.binhth.photocollection.databinding.ItemFilterViewBinding
import com.binhth.photocollection.model.FilterItem
import com.binhth.photocollection.ui.screen.core.BaseRecyclerAdapter
import com.binhth.photocollection.ui.screen.core.diffobject.FilterDiff


class FilterPhotoAdapter(private val itemClick: (FilterItem) -> Unit) :
    BaseRecyclerAdapter<FilterItem>(object :
        FilterDiff() {}) {

    override fun bindFirstTime(binding: ViewDataBinding) {
        if (binding is ItemFilterViewBinding) {
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
            R.layout.item_filter_view, parent, false
        )
    }

    override fun bind(binding: ViewDataBinding, item: FilterItem) {
        if (binding is ItemFilterViewBinding) binding.item = item
    }
}
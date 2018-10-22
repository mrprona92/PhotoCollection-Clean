package com.binhth.photocollection.ui.screen.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.binhth.photocollection.R
import com.binhth.photocollection.databinding.ItemCollectionBinding
import com.binhth.photocollection.model.CollectionItem
import com.binhth.photocollection.ui.screen.core.BaseRecyclerAdapter
import com.binhth.photocollection.ui.screen.core.diffobject.CollectionDiff


class ListCollectionAdapter(private val itemClick: (CollectionItem) -> Unit) :
    BaseRecyclerAdapter<CollectionItem>(object :
        CollectionDiff() {}) {

    override fun bindFirstTime(binding: ViewDataBinding) {
        if (binding is ItemCollectionBinding) {
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
            R.layout.item_collection, parent, false
        )
    }

    override fun bind(binding: ViewDataBinding, item: CollectionItem) {
        if (binding is ItemCollectionBinding) binding.item = item
    }
}
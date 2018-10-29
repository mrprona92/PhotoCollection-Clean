package com.binhth.photocollection.ui.screen.photoeditor.tooledit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.binhth.photocollection.R
import com.binhth.photocollection.databinding.ItemEditingToolsBinding
import com.binhth.photocollection.model.ToolItem
import com.binhth.photocollection.ui.screen.core.BaseRecyclerAdapter
import com.binhth.photocollection.ui.screen.core.diffobject.ToolItemDiff


class ToolEditAdapter(private val itemClick: (ToolItem) -> Unit) :
    BaseRecyclerAdapter<ToolItem>(object :
        ToolItemDiff() {}) {

    override fun bindFirstTime(binding: ViewDataBinding) {
        if (binding is ItemEditingToolsBinding) {
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
            R.layout.item_editing_tools, parent, false
        )
    }

    override fun bind(binding: ViewDataBinding, item: ToolItem) {
        if (binding is ItemEditingToolsBinding) binding.item = item
    }
}
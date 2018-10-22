package com.binhth.photocollection.ui.screen.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.binhth.photocollection.R
import com.binhth.photocollection.databinding.ItemPhotoBinding
import com.binhth.photocollection.model.PhotoItem
import com.binhth.photocollection.ui.screen.core.BaseRecyclerAdapter
import com.binhth.photocollection.ui.screen.core.diffobject.PhotoDiff


class ListPhotoAdapter(private val itemClick: (PhotoItem) -> Unit) :
    BaseRecyclerAdapter<PhotoItem>(object :
        PhotoDiff() {}) {
    override fun bindFirstTime(binding: ViewDataBinding) {
        if (binding is ItemPhotoBinding) binding.item?.let { itemClick.invoke(it) }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_photo, parent, false
        )
    }

    override fun bind(binding: ViewDataBinding, item: PhotoItem) {
        if (binding is ItemPhotoBinding) binding.item = item
    }
}
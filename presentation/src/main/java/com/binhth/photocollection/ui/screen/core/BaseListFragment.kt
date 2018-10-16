package com.binhth.photocollection.ui.screen.core

import androidx.databinding.ViewDataBinding
import com.binhth.photocollection.BR
import com.binhth.photocollection.R

abstract class BaseListFragment<View : ViewDataBinding, ViewModel : BaseListViewModel<T>, T> :
    BaseFragment<View, ViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_list_item
}
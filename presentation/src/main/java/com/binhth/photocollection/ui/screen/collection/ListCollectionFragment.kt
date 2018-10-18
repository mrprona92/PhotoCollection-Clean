package com.binhth.photocollection.ui.screen.collection

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.binhth.photocollection.BR
import com.binhth.photocollection.databinding.FragmentListItemBinding
import com.binhth.photocollection.model.CollectionItem
import com.binhth.photocollection.ui.screen.core.BaseListFragment
import com.binhth.photocollection.utils.EndlessScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class ListCollectionFragment : BaseListFragment<FragmentListItemBinding, ListCollectionViewModel, CollectionItem>(),
    SwipeRefreshLayout.OnRefreshListener {
    companion object {
        const val TAG = "ListCollectionFragment"

        fun newInstance() = ListCollectionFragment()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel by viewModel<ListCollectionViewModel>()

    private lateinit var endlessScrollListener: EndlessScrollListener

    override fun initContent(viewBinding: FragmentListItemBinding) {
        viewBinding.viewModel = viewModel
        viewBinding.swipeRefresh.setOnRefreshListener(this@ListCollectionFragment)
        viewModel.apply {
            val listCollectionAdapter = ListCollectionAdapter(itemClick = { TODO("open photo list") })
            val gridLayoutManager = GridLayoutManager(context, 2)

            endlessScrollListener = EndlessScrollListener(onLoadMore = {
                if (isLoading.value == true
                    || isRefresh.value == true
                    || isLoadMore.value == true
                ) else {
                    isLoadMore.value = true
                    viewModel.loadMore()
                }
            })
            viewBinding.recyclerView.apply {
                layoutManager = gridLayoutManager
                adapter = listCollectionAdapter
                addOnScrollListener(endlessScrollListener)
            }

            initLoad()

            isRefresh.observe(this@ListCollectionFragment, Observer {
                viewBinding.swipeRefresh.isRefreshing = it == true
            })

            listItem.observe(this@ListCollectionFragment, Observer {
                val list = ArrayList<CollectionItem>()
                list.addAll(it)
                listCollectionAdapter.submitList(list)
            })

            isLoadFailed.observe(this@ListCollectionFragment, Observer {
                when (isLoadFailed.value) {
                    true -> {
                        endlessScrollListener.apply {
                            resetLoading()
                        }
                    }
                }
            })

        }
        activity?.title = tag
    }


    override fun onRefresh() {
        viewModel.refreshData()
        endlessScrollListener.resetIndex()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.isLoadFailed.removeObservers(this)
        viewModel.listItem.removeObservers(this)
        viewModel.isLoading.removeObservers(this)
        viewModel.isRefresh.removeObservers(this)
    }
}
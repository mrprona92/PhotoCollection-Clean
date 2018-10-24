package com.binhth.photocollection.ui.screen.photo

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.binhth.photocollection.BR
import com.binhth.photocollection.databinding.FragmentListItemBinding
import com.binhth.photocollection.model.PhotoItem
import com.binhth.photocollection.ui.screen.MainActivity
import com.binhth.photocollection.ui.screen.core.BaseListFragment
import com.binhth.photocollection.utils.EndlessScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class ListPhotoFragment : BaseListFragment<FragmentListItemBinding, ListPhotoViewModel, PhotoItem>(),
    SwipeRefreshLayout.OnRefreshListener {
    companion object {
        const val TAG = "ListPhotoFragment"

        const val PHOTOLIST_ID = "photoListId"

        const val IS_CALL_FROM_COLLECTIONLIST = "isFromCollection"

        fun newInstance(photoListId: String, isFromCollection: Boolean): ListPhotoFragment {
            val args = Bundle()
            args.putString(PHOTOLIST_ID, photoListId)
            args.putBoolean(IS_CALL_FROM_COLLECTIONLIST, isFromCollection)
            val fragment = ListPhotoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel by viewModel<ListPhotoViewModel>()

    private lateinit var endlessScrollListener: EndlessScrollListener

    private lateinit var mainActivity: MainActivity

    override fun initContent(viewBinding: FragmentListItemBinding) {
        viewBinding.viewModel = viewModel
        viewBinding.swipeRefresh.setOnRefreshListener(this@ListPhotoFragment)
        viewModel.apply {
            val listPhotoAdapter = ListPhotoAdapter(itemClick = { TODO("open photo detail") })
            val gridLayoutManager = GridLayoutManager(context, 2)
            idCollection.value = arguments?.getString(PHOTOLIST_ID)
            isCallFromCollectionList.value = arguments?.getBoolean(IS_CALL_FROM_COLLECTIONLIST)

            if (activity is MainActivity) {
                mainActivity = activity as MainActivity
            }

            endlessScrollListener = EndlessScrollListener(onLoadMore = {
                viewModel.loadMore()
            })
            viewBinding.recyclerView.apply {
                layoutManager = gridLayoutManager
                adapter = listPhotoAdapter
                addOnScrollListener(endlessScrollListener)
            }

            initLoad()

            isRefresh.observe(this@ListPhotoFragment, Observer {
                viewBinding.swipeRefresh.isRefreshing = it == true
            })

            listItem.observe(this@ListPhotoFragment, Observer {
                val list = ArrayList<PhotoItem>()
                list.addAll(it)
                listPhotoAdapter.submitList(list)
            })

            isLoadFailed.observe(this@ListPhotoFragment, Observer {
                when (isLoadFailed.value) {
                    true -> {
                        endlessScrollListener.apply {
                            resetLoading()
                        }
                    }
                }
            })

            isCallFromCollectionList.observe(this@ListPhotoFragment, Observer {
                when (isCallFromCollectionList.value) {
                    true -> {
                        mainActivity.showBackButton(true)
                    }
                }
            })
            queryString.observe(this@ListPhotoFragment, Observer {
                refreshData()
            })
        }
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
        if (viewModel.isCallFromCollectionList.value == true) {
            mainActivity.showBackButton(false)
        }
    }
}
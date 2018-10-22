package com.binhth.photocollection.ui.screen.core

import androidx.lifecycle.MutableLiveData
import com.binhth.photocollection.data.Constants

abstract class BaseListViewModel<T> : BaseViewModel() {
    var currentPage = MutableLiveData<Int>().apply { value = 0 }
    val isLoadMore = MutableLiveData<Boolean>().apply { value = false }
    val isLoadFailed = MutableLiveData<Boolean>().apply { value = false }
    val isRefresh = MutableLiveData<Boolean>().apply { value = false }
    val listItem = MutableLiveData<ArrayList<T>>()
    val isNoMoreData = MutableLiveData<Boolean>().apply { value = false }

    private fun isFirstLoading() = currentPage.value == 0
            && (listItem.value == null || listItem.value?.size == 0)

    fun initLoad() {
        if (isFirstLoading()) {
            isLoading.value = true
            requestData(1)
        }
    }

    abstract fun requestData(page: Int)

    fun refreshData() {
        isRefresh.value = true
        isLoadMore.value = false
        isNoMoreData.value = false
        requestData(1)
    }

    fun loadMore() {
        if (isLoading.value == true
            || isRefresh.value == true
            || isLoadMore.value == true
            || isNoMoreData.value == true
        ) else {
            isLoadMore.value = true
            requestData(currentPage.value?.plus(1) ?: 1)
        }
    }

    open fun onLoadSuccess(page: Int, items: List<T>) {
        super.onLoadSuccess()
        isLoadMore.value = false
        isRefresh.value = false
        isLoadFailed.value = false
        currentPage.value = page

        //Case items.isEmpty for case result of load more = multi PAGE_SIZE
        if (items.size < Constants.PAGE_SIZE || items.isEmpty()) {
            isNoMoreData.value = true
        }
        //Reassign value of list with temp list from new value
        val tempList = listItem.value ?: arrayListOf()
        tempList.addAll(items)
        listItem.value = tempList
    }

    override fun onLoadFail(e: Throwable) {
        super.onLoadFail(e)
        isRefresh.value = false
        isLoadMore.value = false
        isLoadFailed.value = true
    }
}
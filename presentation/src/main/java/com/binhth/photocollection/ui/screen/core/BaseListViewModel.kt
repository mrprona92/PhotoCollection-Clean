package com.binhth.photocollection.ui.screen.core

import androidx.lifecycle.MutableLiveData

abstract class BaseListViewModel<T> : BaseViewModel() {
    var currentPage = MutableLiveData<Int>().apply { value = 0 }
    val isLoadMore = MutableLiveData<Boolean>().apply { value = false }
    val isLoadFailed = MutableLiveData<Boolean>().apply { value = false }
    val isRefresh = MutableLiveData<Boolean>().apply { value = false }
    val listItem = MutableLiveData<ArrayList<T>>()

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
        requestData(1)
    }

    fun loadMore(page: Int) {
        isLoadMore.value = true
        requestData(page)
    }


    override fun onLoadSuccess(page: Int) {
        super.onLoadSuccess(page)
        isLoadMore.value = false
        isRefresh.value = false
        isLoadFailed.value = false
        currentPage.value = page
    }

    override fun onLoadFail(e: Throwable) {
        super.onLoadFail(e)
        isRefresh.value = false
        isLoadMore.value = false
        isLoadFailed.value = true
        currentPage.value?.minus(1)
    }
}
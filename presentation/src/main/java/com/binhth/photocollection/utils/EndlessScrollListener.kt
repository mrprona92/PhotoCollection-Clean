package com.binhth.photocollection.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class EndlessScrollListener(val onLoadMore: (page: Int) -> Unit) : RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private val visibleThreshold = 8
    var firstVisibleItem: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var loading: Boolean = false

    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = recyclerView.layoutManager?.itemCount ?: 0

        if (recyclerView.layoutManager is LinearLayoutManager) {
            firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        } else if (recyclerView.layoutManager is GridLayoutManager) {
            firstVisibleItem = (recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
        }

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {

            currentPage++

            onLoadMore.invoke(currentPage)

            loading = true
        }
    }

    fun restoreIndex(page: Int) {
        currentPage = page
        loading = false
    }

    fun resetIndex() {
        currentPage = 1
        totalItemCount = 0
        previousTotal = 0
        firstVisibleItem = 0
        visibleItemCount = 0
        loading = false
    }

    companion object {
        var TAG = EndlessScrollListener::class.java.simpleName
    }
}
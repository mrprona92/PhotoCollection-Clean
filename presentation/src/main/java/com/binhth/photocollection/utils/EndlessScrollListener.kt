package com.binhth.photocollection.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class EndlessScrollListener(val onLoadMore: () -> Unit) : RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private val visibleThreshold = 3
    var firstVisibleItem: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var loading: Boolean = false

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

            onLoadMore.invoke()

            loading = true
        }
    }

    fun resetIndex() {
        totalItemCount = 0
        previousTotal = 0
        firstVisibleItem = 0
        visibleItemCount = 0
        loading = false
    }

    companion object {
        var TAG = EndlessScrollListener::class.java.simpleName
    }

    fun resetLoading() {
        loading = false
    }
}
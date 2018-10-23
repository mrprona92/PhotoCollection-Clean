package com.binhth.photocollection.domain.repository

import com.binhth.photocollection.domain.base.Repository
import com.binhth.photocollection.domain.model.SearchHistory
import io.reactivex.Single

interface SearchHistoryRepository : Repository {

    fun getSearchHistory(): Single<List<SearchHistory>>

    fun saveSearchHistory(listSearched: List<SearchHistory>): List<Long>
}
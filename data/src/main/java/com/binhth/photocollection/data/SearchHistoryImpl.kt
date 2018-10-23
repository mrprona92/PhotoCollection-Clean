package com.binhth.photocollection.data

import com.binhth.photocollection.data.local.db.dao.SearchDao
import com.binhth.photocollection.data.model.SearchHistoryMapper
import com.binhth.photocollection.domain.model.SearchHistory
import com.binhth.photocollection.domain.repository.SearchHistoryRepository
import io.reactivex.Single

class SearchHistoryImpl constructor(
    private val searchDao: SearchDao,
    private val searchMapper: SearchHistoryMapper
) : SearchHistoryRepository {

    override fun getSearchHistory(): Single<List<SearchHistory>> = searchDao.findSearchHistory()
        .map { response ->
            response.map { searchMapper.mapToDomain(it) }
        }

    override fun saveSearchHistory(listSearched: List<SearchHistory>): List<Long> {
        return searchDao.insertAll(listSearched.map { searchMapper.mapToEntity(it) })
    }
}

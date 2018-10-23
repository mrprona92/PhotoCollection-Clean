package com.binhth.photocollection.domain.usecase.collection

import com.binhth.photocollection.domain.model.SearchHistory
import com.binhth.photocollection.domain.repository.SearchHistoryRepository
import com.binhth.photocollection.domain.usecase.Constants
import com.binhth.photocollection.domain.usecase.UseCase
import io.reactivex.Single

open class SaveSearchHistoryUseCase constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : UseCase<SaveSearchHistoryUseCase.Params?, Single<List<Long>>>() {

    override fun onCleared() {
        // if Y want subscribe in UseCase
        // Please unSubscribe it
    }

    override fun createObservable(params: Params?): Single<List<Long>> {
        params?.apply {
            return Single.just(searchHistoryRepository.saveSearchHistory(params.listSearch))
        }
        return Single.error { Throwable(Constants.DATA_ERROR) }
    }

    data class Params(val listSearch: List<SearchHistory>)
}
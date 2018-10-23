package com.binhth.photocollection.domain.usecase.collection

import com.binhth.photocollection.domain.model.SearchHistory
import com.binhth.photocollection.domain.repository.SearchHistoryRepository
import com.binhth.photocollection.domain.usecase.Constants
import com.binhth.photocollection.domain.usecase.UseCase
import io.reactivex.Single

open class GetSearchHistoryUseCase constructor(
    private val historyRepository: SearchHistoryRepository
) : UseCase<GetSearchHistoryUseCase.Params?, Single<List<SearchHistory>>>() {

    override fun onCleared() {
        // if Y want subscribe in UseCase
        // Please unSubscribe it
    }

    override fun createObservable(params: Params?): Single<List<SearchHistory>> {
        params?.apply {
            return historyRepository.getSearchHistory()
        }
        return Single.error { Throwable(Constants.DATA_ERROR) }
    }

    class Params
}
package com.binhth.photocollection.domain.usecase.collection

import com.binhth.photocollection.domain.model.Collection
import com.binhth.photocollection.domain.repository.CollectionRepository
import com.binhth.photocollection.domain.usecase.Constants
import com.binhth.photocollection.domain.usecase.UseCase
import io.reactivex.Single

open class GetCollectionsUseCase constructor(
    private val collectionRepository: CollectionRepository
) : UseCase<GetCollectionsUseCase.Params?, Single<List<Collection>>>() {

    override fun onCleared() {
        // if Y want subscribe in UseCase
        // Please unSubscribe it
    }

    override fun createObservable(params: Params?): Single<List<Collection>> {
        params?.apply {
            return collectionRepository.getCollections(params.page)
        }
        return Single.error { Throwable(Constants.PARAMS_ERROR) }
    }

    data class Params(val page: Int)
}
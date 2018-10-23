package com.binhth.photocollection.domain.usecase.collection

import com.binhth.photocollection.domain.model.Photo
import com.binhth.photocollection.domain.repository.CollectionRepository
import com.binhth.photocollection.domain.usecase.Constants
import com.binhth.photocollection.domain.usecase.UseCase
import io.reactivex.Single

open class SearchPhotosUseCase constructor(
    private val collectionRepository: CollectionRepository
) : UseCase<SearchPhotosUseCase.Params?, Single<List<Photo>>>() {

    override fun onCleared() {
        // if Y want subscribe in UseCase
        // Please unSubscribe it
    }

    override fun createObservable(params: Params?): Single<List<Photo>> {
        params?.apply {
            return collectionRepository.searchPhotos(params.photoName, params.page)
        }
        return Single.error { Throwable(Constants.PARAMS_ERROR) }
    }

    data class Params(val photoName: String, val page: Int)
}
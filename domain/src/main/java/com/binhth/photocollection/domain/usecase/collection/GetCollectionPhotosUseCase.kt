package com.binhth.photocollection.domain.usecase.collection

import com.binhth.photocollection.domain.model.Photo
import com.binhth.photocollection.domain.repository.CollectionRepository
import com.binhth.photocollection.domain.usecase.UseCase
import io.reactivex.Single

open class GetCollectionPhotosUseCase constructor(
    private val collectionRepository: CollectionRepository
) : UseCase<GetCollectionPhotosUseCase.Params?, Single<List<Photo>>>() {

    override fun onCleared() {
        // if Y want subscribe in UseCase
        // Please unSubscribe it
    }

    override fun createObservable(params: Params?): Single<List<Photo>> {
        return collectionRepository.getCollectionPhotos(params?.id ?: "", params?.page ?: "")
    }

    data class Params(val id: String, val page: String)
}
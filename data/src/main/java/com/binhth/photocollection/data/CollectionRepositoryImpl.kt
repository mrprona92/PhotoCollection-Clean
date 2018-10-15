package com.binhth.photocollection.data

import com.binhth.photocollection.data.model.CollectionEntityMapper
import com.binhth.photocollection.data.remote.api.PhotoCollectionApi
import com.binhth.photocollection.domain.model.Collection
import com.binhth.photocollection.domain.repository.CollectionRepository
import io.reactivex.Single

class CollectionRepositoryImpl constructor(
    private val photoCollectionApi: PhotoCollectionApi,
    private val collectionMapper: CollectionEntityMapper
) : CollectionRepository {
    override fun getCollections(page: String): Single<List<Collection>> {
        return photoCollectionApi.requestCollections(page)
            .map { response ->
                response.map { collectionMapper.mapToDomain(it) }
            }
            .doOnError { Throwable("Errors") }
    }
}

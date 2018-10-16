package com.binhth.photocollection.data

import com.binhth.photocollection.data.model.CollectionEntityMapper
import com.binhth.photocollection.data.model.PhotoEntityMapper
import com.binhth.photocollection.data.remote.api.PhotoCollectionApi
import com.binhth.photocollection.domain.model.Collection
import com.binhth.photocollection.domain.model.Photo
import com.binhth.photocollection.domain.repository.CollectionRepository
import io.reactivex.Single

class CollectionRepositoryImpl constructor(
    private val photoCollectionApi: PhotoCollectionApi,
    private val collectionMapper: CollectionEntityMapper,
    private val photoMapper: PhotoEntityMapper
) : CollectionRepository {
    override fun getCollectionPhotos(collectionId: String, page: String): Single<List<Photo>> {
        return photoCollectionApi.requestCollectionPhotos(collectionId, page)
            .map { response ->
                response.map { photoMapper.mapToDomain(it) }
            }
            .doOnError { Throwable("Errors") }
    }

    override fun getCollections(page: String): Single<List<Collection>> {
        return photoCollectionApi.requestCollections(page)
            .map { response ->
                response.map { collectionMapper.mapToDomain(it) }
            }
            .doOnError { Throwable("Errors") }
    }
}

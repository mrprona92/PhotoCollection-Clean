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
    override fun searchPhotos(photoName: String, page: Int): Single<List<Photo>> =
        photoCollectionApi.requestSearchPhotos(photoName, page).map { response ->
            response.listPhotos?.map { photoMapper.mapToDomain(it) }
        }

    override fun searchCollections(collectionName: String, page: Int): Single<List<Collection>> =
        photoCollectionApi.requestSearchCollections(collectionName, page)
            .map { response ->
                response.listCollecion?.map { collectionMapper.mapToDomain(it) }
            }

    override fun getCollectionPhotos(collectionId: String, page: Int): Single<List<Photo>> {
        return photoCollectionApi.requestCollectionPhotos(collectionId, page)
            .map { response ->
                response.map { photoMapper.mapToDomain(it) }
            }
            .doOnError { Throwable("Errors") }
    }

    override fun getCollections(page: Int): Single<List<Collection>> {
        return photoCollectionApi.requestCollections(page)
            .map { response ->
                response.map { collectionMapper.mapToDomain(it) }
            }
            .doOnError { Throwable("Errors") }
    }
}

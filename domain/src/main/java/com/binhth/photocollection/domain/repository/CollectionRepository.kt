package com.binhth.photocollection.domain.repository

import com.binhth.photocollection.domain.base.Repository
import com.binhth.photocollection.domain.model.Collection
import com.binhth.photocollection.domain.model.Photo
import io.reactivex.Single

interface CollectionRepository : Repository {

    fun getCollections(page: String): Single<List<Collection>>

    fun getCollectionPhotos(collectionId: String, page: String): Single<List<Photo>>
}
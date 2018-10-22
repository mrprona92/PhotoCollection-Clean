package com.binhth.photocollection.data.remote.api

import com.binhth.photocollection.data.Constants
import com.binhth.photocollection.data.model.CollectionEntity
import com.binhth.photocollection.data.model.PhotoEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoCollectionApi {

    @GET(Constants.COLLECTION_LIST)
    fun requestCollections(
        @Query(Constants.PAGE) page: Int
    ): Single<List<CollectionEntity>>

    @GET(Constants.COLLECTION_PHOTOS)
    fun requestCollectionPhotos(
        @Path(Constants.ID) id: String,
        @Query(Constants.PAGE) page: String,
        @Query(Constants.PER_PAGE) pageSize: Int = Constants.PAGE_SIZE
    ): Single<List<PhotoEntity>>
}
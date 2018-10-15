package com.binhth.photocollection.data.remote.api

import com.binhth.photocollection.data.Constants
import com.binhth.photocollection.data.model.CollectionEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoCollectionApi {

    @GET(Constants.COLLECTION_LIST)
    fun requestCollections(
        @Query(Constants.PAGE) page: String
    ): Single<List<CollectionEntity>>
}
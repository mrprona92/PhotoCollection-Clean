package com.binhth.photocollection.data.remote.api

import com.binhth.photocollection.data.Constants
import com.binhth.photocollection.data.model.CollectionEntity
import com.binhth.photocollection.data.model.PhotoEntity
import com.binhth.photocollection.data.remote.response.SearchCollecionResponse
import com.binhth.photocollection.data.remote.response.SearchPhotoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoCollectionApi {

    @GET(Constants.COLLECTION_LIST)
    fun requestCollections(
        @Query(Constants.PAGE) page: Int,
        @Query(Constants.PER_PAGE) pageSize: Int = Constants.PAGE_SIZE
    ): Single<List<CollectionEntity>>

    @GET(Constants.COLLECTION_PHOTOS)
    fun requestCollectionPhotos(
        @Path(Constants.ID) id: String,
        @Query(Constants.PAGE) page: Int,
        @Query(Constants.PER_PAGE) pageSize: Int = Constants.PAGE_SIZE
    ): Single<List<PhotoEntity>>

    @GET(Constants.SEARCH_COLLECTIONS)
    fun requestSearchCollections(
        @Query(Constants.QUERY) query: String,
        @Query(Constants.PAGE) page: Int,
        @Query(Constants.PER_PAGE) pageSize: Int = Constants.PAGE_SIZE
    ): Single<SearchCollecionResponse>

    @GET(Constants.SEARCH_PHOTOS)
    fun requestSearchPhotos(
        @Query(Constants.QUERY) query: String,
        @Query(Constants.PAGE) page: Int,
        @Query(Constants.PER_PAGE) pageSize: Int = Constants.PAGE_SIZE
    ): Single<
            SearchPhotoResponse>
}
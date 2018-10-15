package com.binhth.photocollection.domain.repository

import com.binhth.photocollection.domain.base.Repository
import com.binhth.photocollection.domain.model.Collection
import io.reactivex.Single

interface CollectionRepository : Repository {

    fun getCollections(page: String): Single<List<Collection>>
}
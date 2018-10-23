package com.binhth.photocollection.domain.di

import com.binhth.photocollection.domain.usecase.collection.GetCollectionPhotosUseCase
import com.binhth.photocollection.domain.usecase.collection.GetCollectionsUseCase
import com.binhth.photocollection.domain.usecase.collection.SearchCollectionsUseCase
import com.binhth.photocollection.domain.usecase.collection.SearchPhotosUseCase
import org.koin.dsl.module.module

val useCaseModule = module(override = true) {
    single { GetCollectionsUseCase(get()) }
    single { GetCollectionPhotosUseCase(get()) }
    single { SearchCollectionsUseCase(get()) }
    single { SearchPhotosUseCase(get()) }
}
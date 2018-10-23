package com.binhth.photocollection.domain.di

import com.binhth.photocollection.domain.usecase.collection.*
import org.koin.dsl.module.module

val useCaseModule = module(override = true) {
    single { GetCollectionsUseCase(get()) }
    single { GetCollectionPhotosUseCase(get()) }
    single { SearchCollectionsUseCase(get()) }
    single { SearchPhotosUseCase(get()) }
    single { SaveSearchHistoryUseCase(get()) }
    single { GetSearchHistoryUseCase(get()) }
}
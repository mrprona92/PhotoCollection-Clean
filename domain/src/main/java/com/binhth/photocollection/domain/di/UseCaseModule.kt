package com.binhth.photocollection.domain.di

import com.binhth.photocollection.domain.usecase.collection.GetCollectionsUseCase
import org.koin.dsl.module.module

val useCaseModule = module(override = true) {
    single { GetCollectionsUseCase(get()) }
}
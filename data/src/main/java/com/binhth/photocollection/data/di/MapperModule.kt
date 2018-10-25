package com.binhth.photocollection.data.di

import com.binhth.photocollection.data.model.*
import org.koin.dsl.module.module

val mapperModule = module(override = true) {
    single { UrlsEntityMapper() }
    single { UserEntityMapper() }
    single { SearchHistoryMapper() }
    single { CoverPhotoEntityMapper(get()) }
    single { CollectionEntityMapper(get()) }
    single { PhotoEntityMapper(get(), get()) }
}
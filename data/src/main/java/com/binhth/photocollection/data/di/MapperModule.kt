package com.binhth.photocollection.data.di

import com.binhth.photocollection.data.model.CollectionEntityMapper
import com.binhth.photocollection.data.model.PhotoEntityMapper
import org.koin.dsl.module.module

val mapperModule = module(override = true) {
    single { CollectionEntityMapper() }
    single { PhotoEntityMapper() }
}
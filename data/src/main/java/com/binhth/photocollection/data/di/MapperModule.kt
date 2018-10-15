package com.binhth.photocollection.data.di

import com.binhth.photocollection.data.model.CollectionEntityMapper
import org.koin.dsl.module.module

val mapperModule = module(override = true) {
    single { CollectionEntityMapper() }
}
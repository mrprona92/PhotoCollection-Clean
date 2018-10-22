package com.binhth.photocollection.di

import com.binhth.photocollection.model.CollectionItemMapper
import com.binhth.photocollection.model.PhotoItemMapper
import org.koin.dsl.module.module

val itemMapperModule = module(override = true) {
    single { CollectionItemMapper() }
    single { PhotoItemMapper() }
}

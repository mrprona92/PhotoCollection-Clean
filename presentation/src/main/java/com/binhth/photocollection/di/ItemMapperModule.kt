package com.binhth.photocollection.di

import com.binhth.photocollection.model.CollectionItemMapper
import org.koin.dsl.module.module

val itemMapperModule = module(override = true) {
    single { CollectionItemMapper() }
}
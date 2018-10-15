package com.binhth.photocollection.di

import android.content.res.Resources
import com.binhth.photocollection.MainApplication
import org.koin.dsl.module.module

val appModule = module(override = true) {
    single { createResources(get()) }
}

fun createResources(application: MainApplication): Resources = application.resources

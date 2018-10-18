package com.binhth.photocollection.di

import com.binhth.photocollection.ui.screen.MainActivityViewModel
import com.binhth.photocollection.ui.screen.collection.ListCollectionViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module(override = true) {
    viewModel { MainActivityViewModel() }
    viewModel { ListCollectionViewModel(get(),get()) }
}
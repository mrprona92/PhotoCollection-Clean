package com.binhth.photocollection.di

import com.binhth.photocollection.ui.screen.MainActivityViewModel
import com.binhth.photocollection.ui.screen.collection.ListCollectionViewModel
import com.binhth.photocollection.ui.screen.photo.ListPhotoViewModel
import com.binhth.photocollection.ui.screen.photodetail.PhotoDetailsViewModel
import com.binhth.photocollection.ui.screen.search.SearchViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module(override = true) {
    viewModel { MainActivityViewModel() }
    viewModel { ListCollectionViewModel(get(), get(), get()) }
    viewModel { ListPhotoViewModel(get(), get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { PhotoDetailsViewModel(get()) }
}
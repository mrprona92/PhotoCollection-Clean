package com.binhth.photocollection.di

import com.binhth.photocollection.ui.screen.MainActivityViewModel
import com.binhth.photocollection.ui.screen.collection.ListCollectionViewModel
import com.binhth.photocollection.ui.screen.photo.ListPhotoViewModel
import com.binhth.photocollection.ui.screen.photodetail.PhotoDetailsViewModel
import com.binhth.photocollection.ui.screen.photoeditor.PhotoEditorViewModel
import com.binhth.photocollection.ui.screen.photoeditor.brush.BrushViewModel
import com.binhth.photocollection.ui.screen.search.SearchViewModel
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val viewModelModule = module(override = true) {
    viewModel<MainActivityViewModel>()
    viewModel<ListCollectionViewModel>()
    viewModel<ListPhotoViewModel>()
    viewModel<SearchViewModel>()
    viewModel<PhotoDetailsViewModel>()
    viewModel<PhotoEditorViewModel>()
    viewModel<BrushViewModel>()
}
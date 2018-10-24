package com.binhth.photocollection.ui.screen.photo

import androidx.lifecycle.MutableLiveData
import com.binhth.photocollection.domain.usecase.collection.GetCollectionPhotosUseCase
import com.binhth.photocollection.domain.usecase.collection.SearchPhotosUseCase
import com.binhth.photocollection.model.PhotoItem
import com.binhth.photocollection.model.PhotoItemMapper
import com.binhth.photocollection.ui.screen.core.BaseListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ListPhotoViewModel constructor(
    private val searchPhotosUseCase: SearchPhotosUseCase,
    private val getCollectionPhotosUseCase: GetCollectionPhotosUseCase,
    private val photoItemMapper: PhotoItemMapper
) : BaseListViewModel<PhotoItem>() {

    val idCollection = MutableLiveData<String>()
    val isCallFromCollectionList = MutableLiveData<Boolean>()
    val queryString = MutableLiveData<String>()

    override fun requestData(page: Int) {
        //When queryString not empty do search else request list normal
        queryString.value?.let { input ->
            if (input.isNotBlank()) {
                compositeDisposable.add(searchPhotosUseCase.createObservable(
                    SearchPhotosUseCase.Params(input, page = page)
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { items ->
                        items.map { item ->
                            photoItemMapper.mapToPresentation(item)
                        }
                    }
                    .doFinally {
                        isLoading.value = false
                        isRefresh.value = false
                    }
                    .subscribe({ items ->
                        if (page == 1) listItem.value?.clear()
                        if (!items.isEmpty()) {
                            onLoadSuccess(page, items)
                        }
                    }, { e -> onLoadFail(e) })
                )
                return
            }
        }

        idCollection.value.let {
            compositeDisposable.add(getCollectionPhotosUseCase.createObservable(
                GetCollectionPhotosUseCase.Params(id = it, page = page)
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { items ->
                    items.map { item ->
                        photoItemMapper.mapToPresentation(item)
                    }
                }
                .doFinally {
                    isLoading.value = false
                    isRefresh.value = false
                }
                .subscribe({ items ->
                    if (page == 1) listItem.value?.clear()
                    onLoadSuccess(page, items)
                }, { e -> onLoadFail(e) })
            )
        }
    }
}

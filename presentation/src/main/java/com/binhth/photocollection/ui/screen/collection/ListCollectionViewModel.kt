package com.binhth.photocollection.ui.screen.collection

import androidx.lifecycle.MutableLiveData
import com.binhth.photocollection.domain.usecase.collection.GetCollectionsUseCase
import com.binhth.photocollection.domain.usecase.collection.SearchCollectionsUseCase
import com.binhth.photocollection.model.CollectionItem
import com.binhth.photocollection.model.CollectionItemMapper
import com.binhth.photocollection.ui.screen.core.BaseListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ListCollectionViewModel constructor(
    private val getCollectionsUseCase: GetCollectionsUseCase,
    private val searchCollectionUseCase: SearchCollectionsUseCase,
    private val collectionItemMapper: CollectionItemMapper
) : BaseListViewModel<CollectionItem>() {

    val queryString = MutableLiveData<String>()

    override fun requestData(page: Int) {
        //When queryString not empty do search else request list normal
        queryString.value?.let { input ->
            if (input.isNotBlank()) {
                compositeDisposable.add(searchCollectionUseCase.createObservable(
                    SearchCollectionsUseCase.Params(input, page = page)
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { items ->
                        items.map { item ->
                            collectionItemMapper.mapToPresentation(item)
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
            }
            return
        }

        compositeDisposable.add(getCollectionsUseCase.createObservable(
            GetCollectionsUseCase.Params(page = page)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { items ->
                items.map { item ->
                    collectionItemMapper.mapToPresentation(item)
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
    }
}

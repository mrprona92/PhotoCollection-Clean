package com.binhth.photocollection.ui.screen.collection

import com.binhth.photocollection.domain.usecase.collection.GetCollectionsUseCase
import com.binhth.photocollection.model.CollectionItem
import com.binhth.photocollection.model.CollectionItemMapper
import com.binhth.photocollection.ui.screen.core.BaseListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ListCollectionViewModel constructor(
    private val getCollectionsUseCase: GetCollectionsUseCase, private val collectionItemMapper: CollectionItemMapper

) : BaseListViewModel<CollectionItem>() {

    override fun requestData(page: Int) {
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

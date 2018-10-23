package com.binhth.photocollection.ui.screen.search

import androidx.lifecycle.MutableLiveData
import com.binhth.photocollection.domain.model.SearchHistory
import com.binhth.photocollection.domain.usecase.collection.GetSearchHistoryUseCase
import com.binhth.photocollection.domain.usecase.collection.SaveSearchHistoryUseCase
import com.binhth.photocollection.ui.screen.core.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SearchViewModel constructor(
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val saveSearchHistoryUseCase: SaveSearchHistoryUseCase
) : BaseViewModel() {

    val listHistoryItem = MutableLiveData<ArrayList<String>>()
    val queryString = MutableLiveData<String>()

    fun getSearchHistory() {
        compositeDisposable.add(getSearchHistoryUseCase.createObservable(
            GetSearchHistoryUseCase.Params()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { items ->
                items.map { item ->
                    item.query
                }
            }
            .doFinally {
                isLoading.value = false
            }
            .subscribe({ items ->
                listHistoryItem.value?.clear()
                listHistoryItem.value = ArrayList(items)
            }, { e -> onLoadFail(e) })
        )
    }


    fun saveSearchHistory(list: ArrayList<Any?>) {
        val listSearchHistory: ArrayList<SearchHistory> = arrayListOf()
        listSearchHistory.addAll(list.map { s -> SearchHistory(s.toString()) })
        compositeDisposable.add(
            Observable.just(listSearchHistory).subscribeOn(Schedulers.io())
                .subscribe({
                    saveSearchHistoryUseCase.createObservable(
                        SaveSearchHistoryUseCase.Params(it.toList())
                    )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally {
                            isLoading.value = false
                        }
                        .subscribe({
                            //TODO check result insert
                        }, { e -> onLoadFail(e) })
                }, { })
        )
    }

}


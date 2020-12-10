package com.example.translationapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.translationapp.data.model.SearchResult
import com.example.translationapp.domain.interactor.Interactor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val interactor: Interactor = Interactor(),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {
    private val viewState: MutableLiveData<List<SearchResult>> = MutableLiveData()

    fun getViewState(): LiveData<List<SearchResult>> = viewState

    fun getData(word: String) {
        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<SearchResult>>() {
                    override fun onNext(t: List<SearchResult>) {
                        viewState.value = t
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
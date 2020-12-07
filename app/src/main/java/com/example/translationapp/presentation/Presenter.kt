package com.example.translationapp.presentation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.translationapp.data.model.SearchResult
import com.example.translationapp.domain.interactor.Interactor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class Presenter(
    private val view: IView<List<SearchResult>>,
    private val interactor: Interactor = Interactor(),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : LifecycleObserver {

    private var currentView: IView<List<SearchResult>>? = null


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun attachView() {
        if (view != currentView) {
            currentView = view
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun detachView() {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    fun getData(word: String) {
        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<SearchResult>>() {
                    override fun onNext(t: List<SearchResult>) {
                        currentView?.refreshRecyclerView(t)
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }

                })
        )
    }

}
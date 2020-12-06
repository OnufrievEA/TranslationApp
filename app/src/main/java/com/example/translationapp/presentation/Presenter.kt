package com.example.translationapp.presentation

import com.example.translationapp.data.model.SearchResult
import com.example.translationapp.domain.interactor.Interactor
import io.reactivex.Observable

class Presenter(private val interactor: Interactor) {
    fun getData(word: String): Observable<List<SearchResult>> {
        return interactor.getData(word)
    }
}
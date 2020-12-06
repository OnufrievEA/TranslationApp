package com.example.translationapp.domain.interactor

import com.example.translationapp.data.model.SearchResult
import com.example.translationapp.domain.repository.IRepository
import io.reactivex.Observable

class Interactor(private val repository: IRepository<List<SearchResult>>) {
    fun getData(word: String): Observable<List<SearchResult>> {
        return repository.getData(word)
    }
}
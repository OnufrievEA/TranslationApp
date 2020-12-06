package com.example.translationapp.data.repository

import com.example.translationapp.data.datasource.DataSource
import com.example.translationapp.data.model.SearchResult
import com.example.translationapp.domain.repository.IRepository
import io.reactivex.Observable

class Repository(private val dataSource: DataSource<List<SearchResult>>) :
    IRepository<List<SearchResult>> {
    override fun getData(word: String): Observable<List<SearchResult>> {
        return dataSource.getData(word)
    }
}
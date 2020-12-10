package com.example.translationapp.data.repository

import com.example.translationapp.data.datasource.DataSource
import com.example.translationapp.domain.repository.IRepository
import io.reactivex.Observable

class Repository<T>(private val dataSource: DataSource<T>) :
    IRepository<T> {
    override fun getData(word: String): Observable<T> {
        return dataSource.getData(word)
    }
}
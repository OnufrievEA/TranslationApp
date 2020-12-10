package com.example.translationapp.data.datasource

import io.reactivex.Observable

interface DataSource<T> {

    fun getData(word: String): Observable<T>
}

package com.example.translationapp.domain.repository

import io.reactivex.Observable

interface IRepository<T> {

    fun getData(word: String): Observable<T>
}
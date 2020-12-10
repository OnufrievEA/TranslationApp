package com.example.translationapp.data.datasource.network

import com.example.translationapp.data.datasource.DataSource
import com.example.translationapp.data.model.SearchResult
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitDataSource : DataSource<List<SearchResult>> {

    companion object {
        private const val BASE_URL_LOCATIONS = "https://dictionary.skyeng.ru/api/public/v1/"
        private var api: Api? = null
    }

    init {
        if (api == null) {
            api = Retrofit.Builder()
                .baseUrl(BASE_URL_LOCATIONS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }

    override fun getData(word: String): Observable<List<SearchResult>> {
        return api!!.search(word)
    }
}
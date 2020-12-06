package com.example.translationapp.presentation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.translationapp.R
import com.example.translationapp.data.datasource.network.RetrofitDataSource
import com.example.translationapp.data.repository.Repository
import com.example.translationapp.domain.interactor.Interactor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    private lateinit var presenter: Presenter

    private lateinit var searchBtn: Button
    private lateinit var searchEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter(Interactor(Repository(RetrofitDataSource())))

        searchBtn = findViewById(R.id.search_btn)
        searchEt = findViewById(R.id.search_et)

        searchBtn.setOnClickListener(searchBtnOnClickListener)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = MainAdapter()
        val rv = findViewById<RecyclerView>(R.id.recycler)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }

    private val searchBtnOnClickListener = View.OnClickListener { _ ->
        run {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

            val word = searchEt.text.toString()
            presenter.getData(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { res -> adapter.data = res },
                    { e -> Log.d("aaa", e.localizedMessage) })
        }
    }
}
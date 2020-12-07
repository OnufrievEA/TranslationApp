package com.example.translationapp.presentation

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.translationapp.R
import com.example.translationapp.data.model.SearchResult

class MainActivity : AppCompatActivity(), IView<List<SearchResult>> {

    private lateinit var adapter: MainAdapter
    private lateinit var presenter: Presenter

    private lateinit var searchBtn: Button
    private lateinit var searchEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter(this)
        lifecycle.addObserver(presenter)

        initView()
        initRecyclerView()
    }

    private fun initView() {
        searchBtn = findViewById(R.id.search_btn)
        searchEt = findViewById(R.id.search_et)

        searchBtn.setOnClickListener(searchBtnOnClickListener)
    }

    private fun initRecyclerView() {
        adapter = MainAdapter()
        val rv = findViewById<RecyclerView>(R.id.recycler)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }

    private val searchBtnOnClickListener = View.OnClickListener { _ ->
        run {
            // Убираю панель ввода текста
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(window.decorView.rootView.windowToken, 0)

            val word = searchEt.text.toString()
            presenter.getData(word)
        }
    }

    override fun refreshRecyclerView(data: List<SearchResult>) {
        adapter.data = data
    }
}
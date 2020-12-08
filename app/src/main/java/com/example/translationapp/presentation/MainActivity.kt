package com.example.translationapp.presentation

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.translationapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    private lateinit var searchBtn: Button
    private lateinit var searchEt: EditText

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()
        viewModel.getViewState().observe(this, Observer { adapter.data = it })
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
            viewModel.getData(word)
        }
    }
}
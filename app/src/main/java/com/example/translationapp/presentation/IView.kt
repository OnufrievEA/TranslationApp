package com.example.translationapp.presentation

interface IView<T> {
    fun refreshRecyclerView(data: T)
}
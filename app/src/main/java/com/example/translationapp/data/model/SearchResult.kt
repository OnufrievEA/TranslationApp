package com.example.translationapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResult {

    @SerializedName("text")
    @Expose
    val text: String? = null

    @SerializedName("meanings")
    @Expose
    val meanings: List<Meanings>? = null
}

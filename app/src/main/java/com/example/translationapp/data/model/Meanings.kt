package com.example.translationapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meanings {

    @SerializedName("translation")
    @Expose
    val translation: Translation? = null

    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String? = null
}

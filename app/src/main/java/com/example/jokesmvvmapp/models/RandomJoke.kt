package com.example.jokesmvvmapp.models


import com.google.gson.annotations.SerializedName

data class RandomJoke(
    @SerializedName("type")
    val type: String?,
    @SerializedName("value")
    val value: Joke?
)
package com.dapascript.jakmalltest.data.source.model

import com.google.gson.annotations.SerializedName

data class ChildItemResponse(
    @SerializedName("jokes")
    val jokes: List<Jokes>,
)

data class Jokes(
    @SerializedName("joke")
    val joke: String,
)

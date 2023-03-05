package com.dapascript.jakmalltest.data.source.model

import com.google.gson.annotations.SerializedName

data class ParentItemResponse(
    @SerializedName("categories")
    val categories: List<String>,
)
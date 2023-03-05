package com.dapascript.jakmalltest.data.source.service

import com.dapascript.jakmalltest.data.source.model.ChildItemResponse
import com.dapascript.jakmalltest.data.source.model.ParentItemResponse
import retrofit2.http.GET

interface ApiService {

    @GET("categories")
    suspend fun getParentItem(): ParentItemResponse

    @GET("joke/Pun?type=single&amount=2")
    suspend fun getChildItem(): ChildItemResponse
}
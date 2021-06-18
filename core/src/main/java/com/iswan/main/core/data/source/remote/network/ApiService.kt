package com.iswan.main.core.data.source.remote.network

import com.iswan.main.core.data.source.remote.response.ListTourismResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("list")
    fun getList(): Call<ListTourismResponse>

    @GET("list")
    fun getListR(): Flowable<ListTourismResponse>

    @GET("list")
    suspend fun getListC(): ListTourismResponse
}
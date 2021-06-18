package com.iswan.main.core.data.source.remote

import com.iswan.main.core.data.source.remote.network.ApiConfig
import com.iswan.main.core.data.source.remote.network.ApiService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RemoteDataSourceCTest {

    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        apiService = ApiConfig.provideApiService()
    }

    @Test
    fun getAllTourism() {
        runBlocking {
            val response = RemoteDataSourceC(apiService).getAllTourism()
            response.collect {
                println(it)
            }
        }
    }
}
package com.iswan.main.core.data.source.remote

import android.util.Log
import com.iswan.main.core.data.source.remote.network.ApiResponse
import com.iswan.main.core.data.source.remote.network.ApiService
import com.iswan.main.core.data.source.remote.response.TourismResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceC @Inject constructor(
    private val apiService: ApiService
) {

    /* kotlin flow */
    suspend fun getAllTourism(): Flow<ApiResponse<List<TourismResponse>>> {
        return flow {
            try {
                val response = apiService.getListC()
                val dataArray = response.places
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.places))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("TAG", "getAllTourismC: ${e.message.toString()}" )
            }
        }.flowOn(Dispatchers.IO)
    }

}
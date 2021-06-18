package com.iswan.main.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iswan.main.core.data.source.remote.network.ApiResponse
import com.iswan.main.core.data.source.remote.network.ApiService
import com.iswan.main.core.data.source.remote.response.ListTourismResponse
import com.iswan.main.core.data.source.remote.response.TourismResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource constructor(
    private val apiService: ApiService
) {

    /* using companion object if without injection */
    companion object {
        @Volatile
        private var instance: com.iswan.main.core.data.source.remote.RemoteDataSource? = null

        fun getInstance(service: ApiService): com.iswan.main.core.data.source.remote.RemoteDataSource =
            com.iswan.main.core.data.source.remote.RemoteDataSource.Companion.instance ?: synchronized(this) {
                com.iswan.main.core.data.source.remote.RemoteDataSource.Companion.instance
                    ?: com.iswan.main.core.data.source.remote.RemoteDataSource(service)
            }
    }

    fun getAllTourism(): LiveData<ApiResponse<List<TourismResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<TourismResponse>>>()

        val client = apiService.getList()

        client.enqueue(object : Callback<ListTourismResponse> {

            override fun onResponse(
                call: Call<ListTourismResponse>,
                response: Response<ListTourismResponse>
            ) {
                val dataArray = response.body()?.places
                resultData.value = if (dataArray != null) {
                    ApiResponse.Success(dataArray)
                } else {
                    ApiResponse.Empty
                }
            }

            override fun onFailure(call: Call<ListTourismResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.d("TAG", "onFailure: ${t.message.toString()}")
            }

        })

        return resultData
    }

}


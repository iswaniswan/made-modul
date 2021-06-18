package com.iswan.main.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.iswan.main.core.data.source.remote.network.ApiResponse
import com.iswan.main.core.data.source.remote.network.ApiService
import com.iswan.main.core.data.source.remote.response.TourismResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSourceR(
    private val apiService: ApiService
) {

    /* Rx */
    @SuppressLint("CheckResult")
    fun getAllTourism(): Flowable<ApiResponse<List<TourismResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<TourismResponse>>>()

        /* get data from network */
        val client = apiService.getListR()

        /* with rx */
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                val dataArray = it.places
                resultData.onNext(
                    if (dataArray.isNotEmpty()) {
                        ApiResponse.Success(dataArray)
                    } else {
                        ApiResponse.Empty
                    }
                )
            }, {
                resultData.onNext(ApiResponse.Error(it.message.toString()))
                Log.e("TAG", "RemoteDataSource onError: ${it.toString()}")
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

}
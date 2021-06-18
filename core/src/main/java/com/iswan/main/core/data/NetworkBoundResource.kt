package com.iswan.main.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.iswan.main.core.data.source.remote.network.ApiResponse
import com.iswan.main.core.utils.AppExecutors

abstract class NetworkBoundResource<ResultType, RequestType>(
    private val mExecutors: AppExecutors
) {

    private val result = MediatorLiveData<com.iswan.main.core.data.Resource<ResultType>>()

    init {
        result.value = com.iswan.main.core.data.Resource.Loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = com.iswan.main.core.data.Resource.Success(newData)
                }
            }
        }
    }

    abstract fun loadFromDB(): LiveData<ResultType>

    abstract fun shouldFetch(data: ResultType?): Boolean

    protected open fun onFetchFailed() {}

    protected abstract fun saveCallResult(data: RequestType)

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = com.iswan.main.core.data.Resource.Loading(newData)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiResponse.Success ->
                    mExecutors.diskIO().execute {
                        saveCallResult(response.data)
                        mExecutors.mainThread().execute {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = com.iswan.main.core.data.Resource.Success(newData)
                            }
                        }
                    }
                is ApiResponse.Empty -> mExecutors.mainThread().execute {
                    result.addSource(loadFromDB()) { newData ->
                        result.value = com.iswan.main.core.data.Resource.Success(newData)
                    }
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value =
                            com.iswan.main.core.data.Resource.Error(response.errorMessage, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<com.iswan.main.core.data.Resource<ResultType>> = result


}
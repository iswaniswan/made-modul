package com.iswan.main.core.data


import com.iswan.main.core.data.source.remote.network.ApiResponse
import com.iswan.main.core.utils.AppExecutors
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResourceC<ResultType, RequestType>(
    private val executors: AppExecutors
) {

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected open fun onFetchFailed() {}

    /* Kotlin Flow */
    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) })
        }

    }

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result

}
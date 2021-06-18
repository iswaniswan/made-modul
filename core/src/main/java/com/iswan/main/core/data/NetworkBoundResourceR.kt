package com.iswan.main.core.data

import com.iswan.main.core.data.source.remote.network.ApiResponse

import com.iswan.main.core.utils.AppExecutors
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

abstract class NetworkBoundResourceR<ResultType, RequestType>(
    private val mExecutors: AppExecutors
) {

    private val result = PublishSubject.create<com.iswan.main.core.data.Resource<ResultType>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        val db = dbSource
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe {
                dbSource.unsubscribeOn(Schedulers.io())
                if (shouldFetch(it)) {
                    fetchFromNetwork()
                } else {
                    result.onNext(com.iswan.main.core.data.Resource.Success(it))
                }
            }

        compositeDisposable.add(db)

    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()

        result.onNext(com.iswan.main.core.data.Resource.Loading(null))

        val response = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .subscribe { resp ->
                when(resp) {
                    is ApiResponse.Success -> {
                        saveCallResult(resp.data)
                        val dbSource = loadFromDB()
                        dbSource.subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(1)
                            .subscribe {
                                dbSource.unsubscribeOn(Schedulers.io())
                                result.onNext(com.iswan.main.core.data.Resource.Success(it))
                            }
                    }
                    is ApiResponse.Empty -> {
                        val dbSource = loadFromDB()
                        dbSource.subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(1)
                            .subscribe {
                                dbSource.unsubscribeOn(Schedulers.io())
                                result.onNext(com.iswan.main.core.data.Resource.Success(it))
                            }
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        result.onNext(
                            com.iswan.main.core.data.Resource.Error(
                                resp.errorMessage,
                                null
                            )
                        )
                    }
                }
            }
        compositeDisposable.add(response)
    }

    fun asFlowable(): Flowable<com.iswan.main.core.data.Resource<ResultType>> =
        result.toFlowable(BackpressureStrategy.BUFFER)

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flowable<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): Flowable<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)


}
package com.iswan.main.core.data

import com.iswan.main.core.data.source.local.LocalDataSourceC
import com.iswan.main.core.data.source.remote.RemoteDataSourceC
import com.iswan.main.core.data.source.remote.network.ApiResponse
import com.iswan.main.core.data.source.remote.response.TourismResponse
import com.iswan.main.core.domain.model.Tourism
import com.iswan.main.core.domain.repository.ITourismRepository
import com.iswan.main.core.utils.AppExecutors
import com.iswan.main.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TourismRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSourceC,
    private val localDataSource: com.iswan.main.core.data.source.local.LocalDataSourceC,
    private val appExecutors: AppExecutors
): ITourismRepository {

    /*  Kotlin flow */
    override fun getAllTourism(): Flow<Resource<List<Tourism>>> =
        object : NetworkBoundResourceC<List<Tourism>, List<TourismResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Tourism>> {
                return localDataSource.getAllTourism().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Tourism>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourism()

            override suspend fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }

        }.asFlow()

    override fun getFavouriteTourism(): Flow<List<Tourism>> {
        return localDataSource.getFavouriteTourism().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavouriteTourism(tourism: Tourism, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourismC(tourismEntity, state) }
    }

    /* used if using livedata */
    /*
    companion object {
        @Volatile
        private var instance: TourismRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): TourismRepository =
            instance ?: synchronized(this) {
                instance ?: TourismRepository(remoteData, localData, appExecutors)
            }
    }
     */

    /* menggunakan model dari database */
    /*
    override fun getAllTourism(): LiveData<Resource<List<Tourism>>> =
        object : NetworkBoundResource<List<Tourism>, List<TourismResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Tourism>> {
                return Transformations.map(localDataSource.getAllTourism()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tourism>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourism()

            override fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }
        }.asLiveData()

    override fun getFavoriteTourism(): LiveData<List<Tourism>> {
        return Transformations.map(localDataSource.getFavoriteTourism()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavouriteTourism(tourism: Tourism, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
    }
     */


    /* Rx */
    /*
    override fun getAllTourismR(): Flowable<Resource<List<Tourism>>> =
        object : NetworkBoundResourceR<List<Tourism>, List<TourismResponse>>(appExecutors) {
            override fun loadFromDB(): Flowable<List<Tourism>> {
                return localDataSource.getAllTourismR().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tourism>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourismR()

            override fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourismR(tourismList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

        }.asFlowable()

    override fun getFavouriteTourismR(): Flowable<List<Tourism>> {
        return localDataSource.getFavoriteTourismR().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavouriteTourismR(tourism: Tourism, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourismR(tourismEntity, state) }
    }
     */

}


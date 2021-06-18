package com.iswan.main.core.data.source.local

import com.iswan.main.core.data.source.local.entity.TourismEntity
import com.iswan.main.core.data.source.local.room.TourismDaoC
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceC @Inject constructor(
    private val tourismDao: TourismDaoC
) {

    /* Kotlin Flow */
    fun getAllTourism(): Flow<List<TourismEntity>> = tourismDao.getAllTourism()

    fun getFavouriteTourism(): Flow<List<TourismEntity>> = tourismDao.getFavoriteTourism()

    suspend fun insertTourism(tourismList: List<TourismEntity>) = tourismDao.insertTourism(tourismList)

    fun setFavoriteTourismC(tourism: TourismEntity, state: Boolean) {
        tourism.isFavorite = state
        tourismDao.updateFavouriteTourism(tourism)
    }

}
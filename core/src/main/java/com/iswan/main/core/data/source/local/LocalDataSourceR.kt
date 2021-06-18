package com.iswan.main.core.data.source.local

import com.iswan.main.core.data.source.local.entity.TourismEntity
import com.iswan.main.core.data.source.local.room.TourismDaoR
import io.reactivex.Completable
import io.reactivex.Flowable

class LocalDataSourceR(
    private val tourismDao: com.iswan.main.core.data.source.local.room.TourismDaoR
) {

    /* Rx */
    fun getAllTourism(): Flowable<List<com.iswan.main.core.data.source.local.entity.TourismEntity>> = tourismDao.getAllTourism()

    fun getFavoriteTourism(): Flowable<List<com.iswan.main.core.data.source.local.entity.TourismEntity>> = tourismDao.getFavoriteTourism()

    fun insertTourism(tourismList: List<com.iswan.main.core.data.source.local.entity.TourismEntity>): Completable = tourismDao.insertTourism(tourismList)

    fun setFavoriteTourism(tourism: com.iswan.main.core.data.source.local.entity.TourismEntity, newState: Boolean) {
        tourism.isFavorite = newState
        tourismDao.updateFavoriteTourism(tourism)
    }

}
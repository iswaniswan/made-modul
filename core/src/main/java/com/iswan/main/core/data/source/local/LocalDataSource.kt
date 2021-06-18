package com.iswan.main.core.data.source.local

import androidx.lifecycle.LiveData
import com.iswan.main.core.data.source.local.entity.TourismEntity
import com.iswan.main.core.data.source.local.room.TourismDao


class LocalDataSource constructor(private val tourismDao: com.iswan.main.core.data.source.local.room.TourismDao) {

    /* without injection if using object */
    companion object {
        var instance: com.iswan.main.core.data.source.local.LocalDataSource? = null

        fun getInstance(tourismDao: com.iswan.main.core.data.source.local.room.TourismDao): com.iswan.main.core.data.source.local.LocalDataSource =
            com.iswan.main.core.data.source.local.LocalDataSource.Companion.instance ?: synchronized(this) {
                com.iswan.main.core.data.source.local.LocalDataSource.Companion.instance
                    ?: com.iswan.main.core.data.source.local.LocalDataSource(tourismDao)
            }
    }

    /* LiveData */
    fun getAllTourism(): LiveData<List<com.iswan.main.core.data.source.local.entity.TourismEntity>> = tourismDao.getAllTourism()

    fun getFavoriteTourism(): LiveData<List<com.iswan.main.core.data.source.local.entity.TourismEntity>> = tourismDao.getFavoriteTourism()

    fun insertTourism(tourismList: List<com.iswan.main.core.data.source.local.entity.TourismEntity>) = tourismDao.insertTourism(tourismList)

    fun setFavoriteTourism(tourism: com.iswan.main.core.data.source.local.entity.TourismEntity, state: Boolean) {
        tourism.isFavorite = state
        tourismDao.updateFavoriteTourism(tourism)
    }

}
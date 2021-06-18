package com.iswan.main.core.data.source.local.room

import androidx.room.*
import com.iswan.main.core.data.source.local.entity.TourismEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TourismDaoR {

    /* Rx */
    @Query("SELECT * FROM tourism")
    fun getAllTourism(): Flowable<List<com.iswan.main.core.data.source.local.entity.TourismEntity>>

    @Query("SELECT * FROM tourism where isFavorite = 1")
    fun getFavoriteTourism(): Flowable<List<com.iswan.main.core.data.source.local.entity.TourismEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(tourism: List<com.iswan.main.core.data.source.local.entity.TourismEntity>) : Completable

    @Update
    fun updateFavoriteTourism(tourism: com.iswan.main.core.data.source.local.entity.TourismEntity)

}
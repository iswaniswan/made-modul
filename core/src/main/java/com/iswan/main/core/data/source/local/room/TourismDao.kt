package com.iswan.main.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.iswan.main.core.data.source.local.entity.TourismEntity

@Dao
interface TourismDao {

    /* Live Data */
    @Query("SELECT * FROM tourism")
    fun getAllTourism(): LiveData<List<com.iswan.main.core.data.source.local.entity.TourismEntity>>

    @Query("SELECT * FROM tourism where isFavorite = 1")
    fun getFavoriteTourism(): LiveData<List<com.iswan.main.core.data.source.local.entity.TourismEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(tourism: List<com.iswan.main.core.data.source.local.entity.TourismEntity>)

    @Update
    fun updateFavoriteTourism(tourism: com.iswan.main.core.data.source.local.entity.TourismEntity)

}

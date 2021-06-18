package com.iswan.main.core.domain.repository

import com.iswan.main.core.data.Resource
import com.iswan.main.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface ITourismRepository {

    /* Kotlin flow */
    fun getAllTourism(): Flow<Resource<List<Tourism>>>
    fun getFavouriteTourism(): Flow<List<Tourism>>
    fun setFavouriteTourism(tourism: Tourism, state: Boolean)

    /* LiveData */
    /*
    fun getAllTourism(): LiveData<Resource<List<Tourism>>>
    fun getFavoriteTourism(): LiveData<List<Tourism>>
    fun setFavouriteTourism(tourism: Tourism, state: Boolean)
     */

    /* Rx */
    /*
    fun getAllTourismR(): Flowable<Resource<List<Tourism>>>
    fun getFavouriteTourismR(): Flowable<List<Tourism>>
    fun setFavouriteTourismR(tourism: Tourism, state: Boolean)
     */

}
package com.iswan.main.core.domain.usecase

import com.iswan.main.core.data.Resource
import com.iswan.main.core.domain.model.Tourism
import com.iswan.main.core.domain.repository.ITourismRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TourismInteractor @Inject constructor(
    private val tourismRepository: ITourismRepository
) : TourismUseCase {

    /* kotlin flow */
    override fun getAllTourism(): Flow<Resource<List<Tourism>>> =
        tourismRepository.getAllTourism()

    override fun getFavouriteTourism(): Flow<List<Tourism>> =
        tourismRepository.getFavouriteTourism()

    override fun setFavouriteTourism(tourism: Tourism, state: Boolean) =
        tourismRepository.setFavouriteTourism(tourism, state)


    /* Live data */
    /*
    override fun getAllTourism() =
        tourismRepository.getAllTourism()

    override fun getFavouriteTourism() =
        tourismRepository.getFavoriteTourism()

    override fun setFavouriteTourism(tourism: Tourism, state: Boolean) =
        tourismRepository.setFavouriteTourism(tourism, state)
     */

    /* rx */
    /*
    override fun getAllTourismR() = tourismRepository.getAllTourismR()

    override fun getFavouriteTourismR() = tourismRepository.getFavouriteTourismR()

    override fun setFavouriteTourismR(tourism: Tourism, state: Boolean) =
        tourismRepository.setFavouriteTourismR(tourism, state)
     */
}
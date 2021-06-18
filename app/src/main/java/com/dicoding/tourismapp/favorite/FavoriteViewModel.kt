package com.dicoding.tourismapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.iswan.main.core.domain.usecase.TourismUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(tourismUseCase: TourismUseCase) : ViewModel() {

    /* Live Data */
    /*
    val favoriteTourism = tourismUseCase.getFavouriteTourism()
     */

    /* rx */
    /*
    val favoriteTourismR = LiveDataReactiveStreams.fromPublisher(tourismUseCase.getFavouriteTourismR())
     */

    /* kotlin flow */
    val favoriteTourismC = tourismUseCase.getFavouriteTourism().asLiveData()

}


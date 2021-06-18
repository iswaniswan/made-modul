package com.dicoding.tourismapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.iswan.main.core.domain.usecase.TourismUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(tourismUseCase: TourismUseCase) : ViewModel() {

//    val tourism = tourismUseCase.getAllTourism()
    /* Rx */
//    val tourism = LiveDataReactiveStreams.fromPublisher(tourismUseCase.getAllTourism())

    /* Kotlin flow */
    val tourismC = tourismUseCase.getAllTourism().asLiveData()

}


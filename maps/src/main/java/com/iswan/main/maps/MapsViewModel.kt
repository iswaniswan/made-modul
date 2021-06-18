package com.iswan.main.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.iswan.main.core.domain.usecase.TourismUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MapsViewModel @Inject constructor(
    tourismUseCase: TourismUseCase
): ViewModel() {

    val tourism = tourismUseCase.getAllTourism().asLiveData()

}
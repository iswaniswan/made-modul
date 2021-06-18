package com.dicoding.tourismapp.detail

import androidx.lifecycle.ViewModel
import com.iswan.main.core.domain.model.Tourism
import com.iswan.main.core.domain.usecase.TourismUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailTourismViewModel @Inject constructor(private val tourismUseCase: TourismUseCase) : ViewModel() {

    fun setFavoriteTourism(tourism: Tourism, newStatus: Boolean) =
        tourismUseCase.setFavouriteTourism(tourism, newStatus)

}


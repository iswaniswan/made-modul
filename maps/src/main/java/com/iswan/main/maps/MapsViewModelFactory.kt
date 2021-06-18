package com.iswan.main.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iswan.main.core.domain.usecase.TourismUseCase
import javax.inject.Inject

class MapsViewModelFactory @Inject constructor(
    private val tourismUseCase: TourismUseCase
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                MapsViewModel(tourismUseCase) as T
            }
            else -> throw Throwable("unknown view model class : ${modelClass.name}")
        }

}
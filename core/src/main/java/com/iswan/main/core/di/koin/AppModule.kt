package com.iswan.main.core.di.koin

import com.iswan.main.core.domain.usecase.TourismInteractor
import com.iswan.main.core.domain.usecase.TourismUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

//val useCaseModule = module {
//    factory<TourismUseCase> { TourismInteractor(get()) }
//}
//
//val viewModelModule = module {
//    viewModel { HomeViewModel(get()) }
//    viewModel { FavoriteViewModel(get()) }
//    viewModel { DetailTourismViewModel(get()) }
//}
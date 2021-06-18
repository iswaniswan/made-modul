package com.dicoding.tourismapp.di

import com.iswan.main.core.domain.usecase.TourismUseCase
import dagger.Binds
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface MapsDependencies {

    fun tourismUseCase(): TourismUseCase

}
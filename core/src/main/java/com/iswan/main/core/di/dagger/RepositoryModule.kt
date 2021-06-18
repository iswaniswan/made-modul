package com.iswan.main.core.di.dagger

import com.iswan.main.core.data.TourismRepository
import com.iswan.main.core.domain.repository.ITourismRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/* using hilt */
@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: TourismRepository): ITourismRepository

}

/* using dagger */
/*
@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: TourismRepository): ITourismRepository

}
 */

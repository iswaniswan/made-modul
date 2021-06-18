package com.iswan.main.core.di.dagger

import android.content.Context
import androidx.room.Room
import com.iswan.main.core.data.source.local.room.TourismDaoC
import com.iswan.main.core.data.source.local.room.TourismDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/* using hilt */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TourismDatabase =
        Room.databaseBuilder(
            context,
            TourismDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: TourismDatabase): TourismDaoC =
        database.tourismDao()

}


/* using dagger */
/*
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): TourismDatabase =
        Room.databaseBuilder(
            context,
            TourismDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: TourismDatabase): TourismDaoC =
        database.tourismDao()

}
 */
package com.iswan.main.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iswan.main.core.data.source.local.entity.TourismEntity

@Database(entities = [TourismEntity::class], version = 1, exportSchema = false)
abstract class TourismDatabase : RoomDatabase() {

    abstract fun tourismDao(): TourismDaoC

//    companion object {
//        @Volatile
//        private var INSTANCE: TourismDatabase? = null
//
//        fun getInstance(context: Context): TourismDatabase =
//            INSTANCE ?: synchronized(this) {
//            val instance = Room.databaseBuilder(
//                context.applicationContext,
//                TourismDatabase::class.java,
//                "Tourism.db"
//            )
//                .fallbackToDestructiveMigration()
//                .build()
//            INSTANCE = instance
//            instance
//        }
//    }
}
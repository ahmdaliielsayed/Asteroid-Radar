package com.ahmdalii.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmdalii.asteroidradar.model.AsteroidEarth
import com.ahmdalii.asteroidradar.model.AsteroidImageResponse

@Database(
    entities = [AsteroidImageResponse::class, AsteroidEarth::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val asteroidEarthDatabaseDao: AsteroidEarthDatabaseDao
    abstract val asteroidImageDatabaseDao: AsteroidImageDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "asteroid_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}

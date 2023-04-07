package com.ahmdalii.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.ahmdalii.asteroidradar.Constants.EMPTY
import com.ahmdalii.asteroidradar.Constants.FilterType
import com.ahmdalii.asteroidradar.Constants.FilterType.SAVED
import com.ahmdalii.asteroidradar.Constants.FilterType.TODAY
import com.ahmdalii.asteroidradar.Constants.FilterType.WEEK
import com.ahmdalii.asteroidradar.model.AsteroidEarth
import com.ahmdalii.asteroidradar.model.AsteroidImageResponse

class LocalSourceImpl(context: Context) : LocalSource {

    private val asteroidEarthDao: AsteroidEarthDatabaseDao?
    private val asteroidImageDao: AsteroidImageDatabaseDao?

    init {
        val db: AppDatabase = AppDatabase.getInstance(context)
        asteroidEarthDao = db.asteroidEarthDatabaseDao
        asteroidImageDao = db.asteroidImageDatabaseDao
    }

    override suspend fun insertImage(item: AsteroidImageResponse) {
        clearImageTable()
        asteroidImageDao?.insert(item)
    }

    private suspend fun clearImageTable() {
        asteroidImageDao?.clear()
    }

    override fun getAsteroidImage(): LiveData<AsteroidImageResponse>? {
        return asteroidImageDao?.get()
    }

    override suspend fun insertAsteroidEarth(items: List<AsteroidEarth>) {
        clearAsteroidEarthTable()
        asteroidEarthDao?.insert(items)
    }

    private suspend fun clearAsteroidEarthTable() {
        asteroidEarthDao?.clear()
    }

    override fun getAsteroidEarth(type: FilterType, startDate: String?): LiveData<List<AsteroidEarth>>? {
        return when (type) {
            WEEK -> asteroidEarthDao?.getAllDataByWeek(startDate ?: EMPTY)
            TODAY -> asteroidEarthDao?.getAllDataByToday(startDate ?: EMPTY)
            SAVED -> asteroidEarthDao?.get(startDate ?: EMPTY)
        }
    }
}

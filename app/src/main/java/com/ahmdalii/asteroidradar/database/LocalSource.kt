package com.ahmdalii.asteroidradar.database

import androidx.lifecycle.LiveData
import com.ahmdalii.asteroidradar.Constants.FilterType
import com.ahmdalii.asteroidradar.model.AsteroidEarth
import com.ahmdalii.asteroidradar.model.AsteroidImageResponse

interface LocalSource {

    suspend fun insertImage(item: AsteroidImageResponse)

    fun getAsteroidImage(): LiveData<AsteroidImageResponse>?

    suspend fun insertAsteroidEarth(items: List<AsteroidEarth>)

    fun getAsteroidEarth(type: FilterType, startDate: String? = null): LiveData<List<AsteroidEarth>>?
}

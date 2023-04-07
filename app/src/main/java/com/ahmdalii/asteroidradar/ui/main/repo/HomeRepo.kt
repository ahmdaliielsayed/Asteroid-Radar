package com.ahmdalii.asteroidradar.ui.main.repo

import androidx.lifecycle.LiveData
import com.ahmdalii.asteroidradar.Constants.FilterType
import com.ahmdalii.asteroidradar.model.AsteroidEarth
import com.ahmdalii.asteroidradar.model.AsteroidImageResponse
import com.ahmdalii.asteroidradar.model.AsteroidResponse
import retrofit2.Response

interface HomeRepo {

    suspend fun requestAsteroidData(): Response<AsteroidResponse>

    suspend fun requestAsteroidImage(): Response<AsteroidImageResponse>

    suspend fun insertImage(item: AsteroidImageResponse)

    fun getAsteroidImage(): LiveData<AsteroidImageResponse>?

    suspend fun insertAsteroidEarth(items: List<AsteroidEarth>)

    fun getAsteroidEarth(type: FilterType, startDate: String? = null): LiveData<List<AsteroidEarth>>?
}

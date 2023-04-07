package com.ahmdalii.asteroidradar.api

import com.ahmdalii.asteroidradar.model.AsteroidImageResponse
import com.ahmdalii.asteroidradar.model.AsteroidResponse
import retrofit2.Response

interface RemoteSource {

    suspend fun requestAsteroidData(): Response<AsteroidResponse>

    suspend fun requestAsteroidImages(): Response<AsteroidImageResponse>
}

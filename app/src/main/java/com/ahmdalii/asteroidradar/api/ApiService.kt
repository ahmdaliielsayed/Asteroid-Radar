package com.ahmdalii.asteroidradar.api

import com.ahmdalii.asteroidradar.model.AsteroidImageResponse
import com.ahmdalii.asteroidradar.model.AsteroidResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidData(): Response<AsteroidResponse>

    @GET("planetary/apod")
    suspend fun getAsteroidImage(): Response<AsteroidImageResponse>
}

package com.ahmdalii.asteroidradar.api

import com.ahmdalii.asteroidradar.model.AsteroidImageResponse
import com.ahmdalii.asteroidradar.model.AsteroidResponse
import retrofit2.Response

class AsteroidClient private constructor() : RemoteSource {

    companion object {
        private var instance: AsteroidClient? = null

        fun getInstance(): AsteroidClient {
            return instance ?: AsteroidClient()
        }
    }

    private fun getRetrofit() = BaseRetrofitHelper.getInstance().create(ApiService::class.java)

    override suspend fun requestAsteroidData(): Response<AsteroidResponse> {
        return getRetrofit().getAsteroidData()
    }

    override suspend fun requestAsteroidImages(): Response<AsteroidImageResponse> {
        return getRetrofit().getAsteroidImage()
    }
}

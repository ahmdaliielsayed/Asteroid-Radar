package com.ahmdalii.asteroidradar.ui.main.repo

import androidx.lifecycle.LiveData
import com.ahmdalii.asteroidradar.Constants.FilterType
import com.ahmdalii.asteroidradar.api.RemoteSource
import com.ahmdalii.asteroidradar.database.LocalSource
import com.ahmdalii.asteroidradar.model.AsteroidEarth
import com.ahmdalii.asteroidradar.model.AsteroidImageResponse
import com.ahmdalii.asteroidradar.model.AsteroidResponse
import retrofit2.Response

class HomeRepoImpl private constructor(
    private var remoteSource: RemoteSource,
    private var localSource: LocalSource
) : HomeRepo {

    companion object {
        private var instance: HomeRepo? = null

        fun getInstance(remoteSource: RemoteSource, localSource: LocalSource): HomeRepo {
            return instance ?: HomeRepoImpl(remoteSource, localSource)
        }
    }

    override suspend fun requestAsteroidData(startDate: String, endDate: String): Response<AsteroidResponse> {
        return remoteSource.requestAsteroidData(startDate, endDate)
    }

    override suspend fun requestAsteroidImage(): Response<AsteroidImageResponse> {
        return remoteSource.requestAsteroidImages()
    }

    override suspend fun insertImage(item: AsteroidImageResponse) {
        localSource.insertImage(item)
    }

    override fun getAsteroidImage(): LiveData<AsteroidImageResponse>? {
        return localSource.getAsteroidImage()
    }

    override suspend fun insertAsteroidEarth(items: List<AsteroidEarth>) {
        localSource.insertAsteroidEarth(items)
    }

    override fun getAsteroidEarth(type: FilterType, startDate: String?): LiveData<List<AsteroidEarth>>? {
        return localSource.getAsteroidEarth(type, startDate)
    }
}

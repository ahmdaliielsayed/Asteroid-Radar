package com.ahmdalii.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ahmdalii.asteroidradar.Constants
import com.ahmdalii.asteroidradar.api.AsteroidClient
import com.ahmdalii.asteroidradar.database.LocalSourceImpl

class FetchAndSaveAsteroidsWorkManagerHelper(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "FetchAndSaveAsteroidsWorker"
    }

    override suspend fun doWork(): Result {
        var result = Result.failure()

        // get db object
        val localSourceInstance = LocalSourceImpl(applicationContext)

        // get api object
        val apiInstance = AsteroidClient.getInstance()

        // do background work
        val response = apiInstance.requestAsteroidData()
        if (response.isSuccessful) {
            Constants.createLocalAsteroidList(response)
            val newLocalList = Constants.createLocalAsteroidList(response)
            localSourceInstance.insertAsteroidEarth(newLocalList)

            result = Result.success()
        }

        return result
    }
}

package com.ahmdalii.asteroidradar

import android.app.Application
import com.ahmdalii.asteroidradar.Constants.API_KEY
import com.ahmdalii.asteroidradar.Constants.BASE_URL

class BaseApplication : Application() {

    companion object {
        init {
            System.loadLibrary("asteroid-radar")
        }
    }

    override fun onCreate() {
        super.onCreate()

        BASE_URL = getBaseURL()
        API_KEY = getAPIKey()
    }

    private external fun getBaseURL(): String
    private external fun getAPIKey(): String
}

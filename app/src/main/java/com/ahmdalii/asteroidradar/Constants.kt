package com.ahmdalii.asteroidradar

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.ui.setupWithNavController
import com.ahmdalii.asteroidradar.model.AsteroidEarth
import com.ahmdalii.asteroidradar.model.AsteroidResponse
import retrofit2.Response

object Constants {
    const val API_QUERY_DATE_FORMAT = "yyyy-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val EMPTY = ""

    var BASE_URL = "https://api.nasa.gov/"
    var API_KEY = BuildConfig.API_KEY

    enum class FilterType {
        WEEK,
        TODAY,
        SAVED
    }

    fun createLocalAsteroidList(response: Response<AsteroidResponse>): MutableList<AsteroidEarth> {
        val newLocalList = mutableListOf<AsteroidEarth>()
        response.body()?.nearEarthObjects?.forEach { (key, value) ->
            value.forEach { asteroid ->
                newLocalList.add(
                    AsteroidEarth().apply {
                        id = asteroid.id
                        name = asteroid.name
                        absoluteMagnitudeH = asteroid.absoluteMagnitudeH
                        estimatedDiameter =
                            asteroid.estimatedDiameter?.kilometers?.estimatedDiameterMax
                        relativeVelocity =
                            asteroid.closeApproachData?.get(0)?.relativeVelocity?.kilometersPerSecond?.toDoubleOrNull()
                        distanceFromEarth =
                            asteroid.closeApproachData?.get(0)?.missDistance?.astronomical?.toDoubleOrNull()
                        isPotentiallyHazardousAsteroid =
                            asteroid.isPotentiallyHazardousAsteroid
                        asteroidDate = key
                    }
                )
            }
        }

        return newLocalList
    }
}

fun Fragment.setBaseActivityFragmentsToolbar(title: String, toolbar: Toolbar, textView: TextView) {
    (activity as MainActivity).apply {
        setHasOptionsMenu(true)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        toolbar.setupWithNavController(navController, appBarConfiguration)
        textView.text = title
    }
}

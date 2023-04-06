package com.ahmdalii.asteroidradar.model

import com.google.gson.annotations.SerializedName

data class RelativeVelocity(

    @SerializedName("kilometers_per_hour")
    val kilometersPerHour: String? = null,

    @SerializedName("kilometers_per_second")
    val kilometersPerSecond: String? = null,

    @SerializedName("miles_per_hour")
    val milesPerHour: String? = null
)

package com.ahmdalii.asteroidradar.model

import com.google.gson.annotations.SerializedName

data class Meters(

    @SerializedName("estimated_diameter_max")
    val estimated_diameter_max: Double? = null,

    @SerializedName("estimated_diameter_min")
    val estimated_diameter_min: Double? = null
)

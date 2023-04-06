package com.ahmdalii.asteroidradar.model

import com.google.gson.annotations.SerializedName

data class Kilometers(

    @SerializedName("estimated_diameter_max")
    val estimatedDiameterMax: Double? = null,

    @SerializedName("estimated_diameter_min")
    val estimatedDiameterMin: Double? = null
)

package com.ahmdalii.asteroidradar.model

import com.google.gson.annotations.SerializedName

data class AsteroidResponse(

    @SerializedName("element_count")
    val elementCount: Int? = null,

    @SerializedName("links")
    val links: Links? = null,

    @SerializedName("near_earth_objects")
    var nearEarthObjects: Map<String, MutableList<NearEarthObjects>>? = null
)

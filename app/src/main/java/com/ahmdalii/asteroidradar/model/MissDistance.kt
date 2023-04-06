package com.ahmdalii.asteroidradar.model

import com.google.gson.annotations.SerializedName

data class MissDistance(

    @SerializedName("astronomical")
    val astronomical: String? = null,

    @SerializedName("kilometers")
    val kilometers: String? = null,

    @SerializedName("lunar")
    val lunar: String? = null,

    @SerializedName("miles")
    val miles: String? = null
)

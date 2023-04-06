package com.ahmdalii.asteroidradar.model

import com.google.gson.annotations.SerializedName

data class EstimatedDiameter(

    //    @Embedded(prefix = "feet")
    @SerializedName("feet")
    val feet: Feet? = null,

    //    @Embedded(prefix = "kilometers")
    @SerializedName("kilometers")
    val kilometers: Kilometers? = null,

    //    @Embedded(prefix = "meters")
    @SerializedName("meters")
    val meters: Meters? = null,

    //    @Embedded(prefix = "miles")
    @SerializedName("miles")
    val miles: Miles? = null
)

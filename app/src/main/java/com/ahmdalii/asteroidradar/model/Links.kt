package com.ahmdalii.asteroidradar.model

import com.google.gson.annotations.SerializedName

data class Links(

    @SerializedName("next")
    val next: String? = null,

    @SerializedName("previous")
    val previous: String? = null,

    @SerializedName("self")
    val self: String? = null
)

package com.ahmdalii.asteroidradar.model

import com.google.gson.annotations.SerializedName

data class CloseApproachData(

    @SerializedName("close_approach_date")
    val closeApproachDate: String? = null,

    @SerializedName("close_approach_date_full")
    val closeApproachDateFull: String? = null,

    @SerializedName("epoch_date_close_approach")
    val epochDateCloseApproach: Long? = null,

    @SerializedName("miss_distance")
    val missDistance: MissDistance? = null,

    @SerializedName("orbiting_body")
    val orbitingBody: String? = null,

    @SerializedName("relative_velocity")
    val relativeVelocity: RelativeVelocity? = null
)

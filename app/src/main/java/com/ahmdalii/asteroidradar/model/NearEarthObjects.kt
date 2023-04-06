package com.ahmdalii.asteroidradar.model

import com.google.gson.annotations.SerializedName

data class NearEarthObjects(

    @SerializedName("absolute_magnitude_h")
    val absoluteMagnitudeH: Double? = null,

    @SerializedName("close_approach_data")
    val closeApproachData: List<CloseApproachData>? = null,

    @SerializedName("estimated_diameter")
    val estimatedDiameter: EstimatedDiameter? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("is_potentially_hazardous_asteroid")
    val isPotentiallyHazardousAsteroid: Boolean? = null,

    @SerializedName("is_sentry_object")
    val isSentryObject: Boolean? = null,

    @SerializedName("links")
    val links: Links? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("nasa_jpl_url")
    val nasaJplUrl: String? = null,

    @SerializedName("neo_reference_id")
    val neoReferenceId: String? = null
)

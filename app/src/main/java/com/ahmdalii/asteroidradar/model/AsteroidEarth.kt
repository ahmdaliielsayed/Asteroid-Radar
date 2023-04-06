package com.ahmdalii.asteroidradar.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "asteroid_table")
data class AsteroidEarth(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "asteroidLocal")
    var idLocal: Long? = null,

    @ColumnInfo(name = "asteroidName")
    var name: String? = null,

    @ColumnInfo(name = "asteroidId")
    var id: String? = null,

    @ColumnInfo(name = "asteroidAbsoluteMagnitudeH")
    var absoluteMagnitudeH: Double? = null,

    @ColumnInfo(name = "asteroidEstimatedDiameter")
    var estimatedDiameter: Double? = null,

    @ColumnInfo(name = "asteroidRelativeVelocity")
    var relativeVelocity: Double? = null,

    @ColumnInfo(name = "asteroidDistanceFromEarth")
    var distanceFromEarth: Double? = null,

    @ColumnInfo(name = "asteroidIsPotentiallyHazardousAsteroid")
    var isPotentiallyHazardousAsteroid: Boolean? = null,

    @ColumnInfo(name = "asteroidDate")
    var asteroidDate: String? = null
) : Parcelable

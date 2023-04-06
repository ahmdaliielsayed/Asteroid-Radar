package com.ahmdalii.asteroidradar.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "image_table", indices = [Index(value = ["imageTitle", "imageUrl"], unique = true)])
data class AsteroidImageResponse(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "imageLocal")
    var idLocal: Int? = null,

    @SerializedName("date")
    @ColumnInfo(name = "imageDate")
    val date: String? = null,

    @SerializedName("explanation")
    @ColumnInfo(name = "imageExplanation")
    val explanation: String? = null,

    @SerializedName("hdurl")
    @ColumnInfo(name = "imageHDUrl")
    val hdUrl: String? = null,

    @SerializedName("media_type")
    @ColumnInfo(name = "imageMediaType")
    val mediaType: String? = null,

    @SerializedName("service_version")
    @ColumnInfo(name = "imageServiceVersion")
    val service_version: String? = null,

    @SerializedName("title")
    @ColumnInfo(name = "imageTitle")
    val title: String? = null,

    @SerializedName("url")
    @ColumnInfo(name = "imageUrl")
    val url: String? = null
)

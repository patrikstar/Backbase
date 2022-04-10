package com.backbase.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityModel(
    @SerializedName("country")
    val country: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("_id")
    val id: Int,
    @SerializedName("coord")
    val coordinates: CityCoordinates
) : Parcelable

@Parcelize
data class CityCoordinates(
    @SerializedName("lon")
    val lon: Float,
    @SerializedName("lat")
    val lat: Float
) : Parcelable

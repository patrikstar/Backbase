package com.backbase.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityModel(
    @SerialName("country")
    val country: String,
    @SerialName("name")
    val name: String,
    @SerialName("_id")
    val id: Int,
    @SerialName("coord")
    val coordinates: CityCoordinates
)

@Serializable
data class CityCoordinates(
    @SerialName("lon")
    val lon: Float,
    @SerialName("lat")
    val lat: Float
)

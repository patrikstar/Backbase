package com.backbase.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityDomainModel(
    val country: String,
    val name: String,
    val nameLowercase: String,
    val id: Int,
    val latitude: Float,
    val longitude: Float
): Parcelable

package com.backbase.common

import com.backbase.data.model.CityCoordinates
import com.backbase.data.model.CityDataModel

fun getCitiDataModelList() = listOf(
    CityDataModel(
        country = "UA", name = "Kyiv", id = 0, coordinates = CityCoordinates(lat = 0.1f, lon = 0.4f)
    ),
    CityDataModel(
        country = "UA", name = "Lviv", id = 1, coordinates = CityCoordinates(lat = 0.2f, lon = 0.4f)
    ),
    CityDataModel(
        country = "UA", name = "Kharkiv", id = 2, coordinates = CityCoordinates(lat = 0.3f, lon = 0.4f)
    ),
    CityDataModel(
        country = "UA", name = "Odesa", id = 3, coordinates = CityCoordinates(lat = 0.4f, lon = 0.4f)
    )
)
package com.backbase.data.mapper

import com.backbase.common.mapper.Mapper
import com.backbase.data.model.CityDataModel
import com.backbase.domain.model.CityDomainModel

class CityDataToDomainMapper : Mapper<List<CityDataModel>, List<CityDomainModel>> {
    override fun map(data: List<CityDataModel>): List<CityDomainModel> = data.map {
        CityDomainModel(
            id = it.id,
            name = it.name,
            nameLowercase = it.name.lowercase(),
            country = it.country,
            latitude = it.coordinates.lat,
            longitude = it.coordinates.lon
        )
    }
}
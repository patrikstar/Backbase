package com.backbase.data

import com.backbase.data.model.CityModel
import com.backbase.data.parser.JsonParser
import com.backbase.data.repository.DataRepository
import com.backbase.domain.model.CityDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepositoryImpl(
    private val parser: JsonParser
) : DataRepository {

    private var initialList: List<CityDomainModel> = emptyList()

    override suspend fun parseAndSaveData(): Boolean {
        return withContext(Dispatchers.Default) {
            val parsedList: List<CityModel> = parser.parseJson()
            if (parsedList.isEmpty()) {
                false
            } else {
                mapAndSaveList(parsedList)
                true
            }
        }
    }

    private suspend fun mapAndSaveList(rawData: List<CityModel>) {
        withContext(Dispatchers.Default) {
            val mappedCities = rawData.map {
                CityDomainModel(
                    id = it.id,
                    name = it.name,
                    nameLowercase = it.name.lowercase(),
                    country = it.country,
                    latitude = it.coordinates.lat,
                    longitude = it.coordinates.lon
                )
            }

            val sortedList =
                mappedCities.sortedWith(compareBy({ it.nameLowercase }, { it.country }))

            insertData(sortedList)
        }
    }

    private fun insertData(list: List<CityDomainModel>) {
        initialList = list
    }

    override fun getInitialList(): List<CityDomainModel> = initialList
}

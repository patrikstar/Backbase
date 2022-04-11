package com.backbase.data

import com.backbase.data.mapper.CityDataToDomainMapper
import com.backbase.data.model.CityDataModel
import com.backbase.data.parser.JsonParser
import com.backbase.data.repository.DataRepository
import com.backbase.domain.model.CityDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepositoryImpl(
    private val parser: JsonParser,
    private val mapper: CityDataToDomainMapper
) : DataRepository {

    private var initialList: List<CityDomainModel> = emptyList()

    override suspend fun parseAndSaveData(): Boolean {
        return withContext(Dispatchers.Default) {
            val parsedList: List<CityDataModel> = parser.parseJson()
            if (parsedList.isEmpty()) {
                false
            } else {
                mapAndSaveList(parsedList)
                true
            }
        }
    }

    private suspend fun mapAndSaveList(rawData: List<CityDataModel>) {
        withContext(Dispatchers.Default) {
            val mappedCities = mapper.map(rawData)

            val sortedList = mappedCities.sortedWith(
                compareBy(
                    { it.nameLowercase },
                    { it.country }
                )
            )

            insertData(sortedList)
        }
    }

    private fun insertData(list: List<CityDomainModel>) {
        initialList = list
    }

    override fun getInitialList(): List<CityDomainModel> = initialList
}

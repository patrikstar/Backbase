package com.backbase.data.repository

import com.backbase.data.mapper.CityDataToDomainMapper
import com.backbase.data.model.CityDataModel
import com.backbase.data.parser.AssetsJsonParser
import com.backbase.domain.model.CityDomainModel
import com.backbase.domain.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val JSON_FILE_NAME = "cities.json"

class DataRepositoryImpl(
    private val assetsJsonParser: AssetsJsonParser,
    private val mapper: CityDataToDomainMapper
) : DataRepository {

    private var initialList: List<CityDomainModel> = emptyList()

    /**
     * List is parsed from json file with parser
     * @return true if parsed list is not empty, false otherwise
     **/
    override suspend fun parseAndSaveData(): Boolean {
        return withContext(Dispatchers.Default) {
            val parsedList: List<CityDataModel> = assetsJsonParser.parseJson(JSON_FILE_NAME)
            if (parsedList.isEmpty()) {
                false
            } else {
                mapAndSaveList(parsedList)
                true
            }
        }
    }

    /**
     * List is mapped to domain model and initially sorted
     **/
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

    /**
     * Returns mapped and initially sorted initial list
     **/
    override fun getInitialList(): List<CityDomainModel> = initialList
}

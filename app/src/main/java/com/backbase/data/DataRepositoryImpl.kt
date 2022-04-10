package com.backbase.data

import com.backbase.data.repository.DataRepository
import com.backbase.domain.model.CityDomainModel

class DataRepositoryImpl(): DataRepository {

    private var initialList: List<CityDomainModel> = emptyList()

    override suspend fun insertData(list: List<CityDomainModel>) {
        initialList = list
    }

    override fun getInitialList(): List<CityDomainModel> = initialList
}

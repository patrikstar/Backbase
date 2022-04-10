package com.backbase.data.repository

import com.backbase.domain.model.CityDomainModel

interface DataRepository {

    suspend fun parseAndSaveData(): Boolean

    suspend fun insertData(list: List<CityDomainModel>)

    fun getInitialList(): List<CityDomainModel>

}

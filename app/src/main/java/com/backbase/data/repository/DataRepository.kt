package com.backbase.data.repository

import com.backbase.domain.model.CityDomainModel

interface DataRepository {

    suspend fun parseAndSaveData(): Boolean

    fun getInitialList(): List<CityDomainModel>

}

package com.backbase.domain.repository

import com.backbase.domain.model.CityDomainModel

interface DataRepository {

    suspend fun parseAndSaveData(): Boolean

    fun getInitialList(): List<CityDomainModel>
}

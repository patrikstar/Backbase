package com.backbase.domain.repository

import com.backbase.domain.model.CityDomainModel

interface DomainRepository {

    suspend fun parseAndSaveData(): Boolean

    suspend fun fetchData() : List<CityDomainModel>
}

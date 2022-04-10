package com.backbase.domain.repository

import com.backbase.domain.model.CityDomainModel
import kotlinx.coroutines.flow.Flow

interface DomainRepository {

    suspend fun parseAndSaveData(): Boolean

    suspend fun fetchData()

    suspend fun searchResultFlow(): Flow<List<CityDomainModel>>

    suspend fun searchWithKey(key: String)
}

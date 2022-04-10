package com.backbase.domain

import com.backbase.data.repository.DataRepository
import com.backbase.domain.model.CityDomainModel
import com.backbase.domain.repository.DomainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DomainRepositoryImpl(
    private val dataRepository: DataRepository
) : DomainRepository {

    private var initialList: List<CityDomainModel> = emptyList()

    override suspend fun parseAndSaveData(): Boolean {
        return withContext(Dispatchers.Default) {
            dataRepository.parseAndSaveData()
        }
    }

    override suspend fun fetchData(): List<CityDomainModel> {
        return withContext(Dispatchers.Default) {
            val res = dataRepository.getInitialList()
            initialList = res
            res
        }
    }
}

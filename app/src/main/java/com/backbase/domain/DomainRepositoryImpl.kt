package com.backbase.domain

import com.backbase.data.repository.DataRepository
import com.backbase.domain.model.CityDomainModel
import com.backbase.domain.repository.DomainRepository
import com.backbase.utils.search.customFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import timber.log.Timber

class DomainRepositoryImpl(
    private val dataRepository: DataRepository
) : DomainRepository {

    private var initialList: List<CityDomainModel> = emptyList()

    private val resultFlow = MutableStateFlow(emptyList<CityDomainModel>())

    override suspend fun searchResultFlow(): Flow<List<CityDomainModel>> = resultFlow

    override suspend fun parseAndSaveData(): Boolean = withContext(Dispatchers.Default) {
        dataRepository.parseAndSaveData()
    }

    override suspend fun fetchData() = withContext(Dispatchers.Default) {
        val res = dataRepository.getInitialList()
        initialList = res
        resultFlow.value = res
    }

    override suspend fun searchWithKey(key: String) = withContext(Dispatchers.Default) {
        if (key.isEmpty()) {
            resultFlow.value = initialList
        } else {
            ensureActive()
            Timber.d("START: ${System.currentTimeMillis()} | key: $key")
//            val filteredList =
//                initialList.filter { it.name.startsWith(key, true) } // linear filter to compare
            val filteredList = initialList.customFilter(key)
            Timber.d("END ${System.currentTimeMillis()} | resSize: ${filteredList.size}")

            ensureActive()
            resultFlow.value = filteredList
        }
    }
}

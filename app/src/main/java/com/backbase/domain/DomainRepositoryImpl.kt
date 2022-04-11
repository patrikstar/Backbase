package com.backbase.domain

import com.backbase.domain.repository.DataRepository
import com.backbase.domain.model.CityDomainModel
import com.backbase.domain.repository.DomainRepository
import com.backbase.domain.search.customFilter
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

    /**
     * invoke dataRepository to Parse and save list from json
     **/
    override suspend fun parseAndSaveData(): Boolean = withContext(Dispatchers.Default) {
        dataRepository.parseAndSaveData()
    }

    /**
     * Fetch and save mapped and initially sorted initial list
     **/
    override suspend fun fetchData() = withContext(Dispatchers.Default) {
        val res = dataRepository.getInitialList()
        initialList = res
        resultFlow.value = res
    }

    /**
     * Filter algorithm is based on binary kotlin base binary search and located in [SearchExtensions]
     *
     * in best case it can be 25 times faster than linear search
     * in worst case it is 5% faster than linear search
     *  @param key - search key, if empty returns {initialList}
    **/
    override suspend fun searchWithKey(key: String) = withContext(Dispatchers.Default) {
        if (key.isEmpty()) {
            resultFlow.value = initialList
        } else {
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

package com.backbase.ui.splash

import android.app.Application
import androidx.lifecycle.*
import com.backbase.data.model.CityModel
import com.backbase.data.repository.DataRepository
import com.backbase.domain.model.CityDomainModel
import com.backbase.data.parser.JsonParser
import com.backbase.ui.splash.model.SplashViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(
    app: Application,
    private val dataRepository: DataRepository,
    private val parser: JsonParser
) : AndroidViewModel(app) {

    private val splashLiveData = MutableLiveData<SplashViewState>()

    fun observeSplashLiveData(owner: LifecycleOwner, observer: Observer<SplashViewState>) {
        splashLiveData.observe(owner, observer)
    }

    init {
        getCitiesFromRaw()
    }

    private fun getCitiesFromRaw() {
        viewModelScope.launch {
            parseJsonFile()
        }
    }

    private suspend fun parseJsonFile() = withContext(Dispatchers.Default) {
        val parsedList: List<CityModel> = parser.parseJson()
        if (parsedList.isEmpty()) {
            splashLiveData.postValue(SplashViewState.Error)
        } else {
            mapAndSaveList(parsedList)
        }
    }

    private suspend fun mapAndSaveList(rawData: List<CityModel>) = withContext(Dispatchers.Default) {
        val mappedCities = rawData.map {
            CityDomainModel(
                id = it.id,
                name = it.name,
                nameLowercase = it.name.lowercase(),
                country = it.country,
                latitude = it.coordinates.lat,
                longitude = it.coordinates.lon
            )
        }

        val sortedList =
            mappedCities.sortedWith(compareBy({ it.nameLowercase }, { it.country }))

        dataRepository.insertData(sortedList)
        splashLiveData.postValue(SplashViewState.Success)
    }
}

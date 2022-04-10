package com.backbase.ui.splash

import android.app.Application
import androidx.lifecycle.*
import com.backbase.data.model.CityModel
import com.backbase.data.repository.DataRepository
import com.backbase.domain.model.CityDomainModel
import com.backbase.ui.splash.model.SplashViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import timber.log.Timber
import java.io.IOException

class SplashViewModel(
    app: Application,
    private val json: Json,
    private val dataRepository: DataRepository
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
            getJsonFile()
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    @OptIn(ExperimentalSerializationApi::class)
    private suspend fun getJsonFile() = withContext(Dispatchers.Default) {
        val parsedList: List<CityModel> = try {
            getApplication<Application>().assets.open("cities.json").use { inputStream ->
                json.decodeFromStream(inputStream)
            }
        } catch (ioException: IOException) {
            Timber.e("Get Json file from raw failed!")
            emptyList()
        }
        if (parsedList.isEmpty()) {
            splashLiveData.postValue(SplashViewState.Error)
        } else {
            parseList(parsedList)
        }
    }

    private suspend fun parseList(rawData: List<CityModel>) = withContext(Dispatchers.Default) {
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

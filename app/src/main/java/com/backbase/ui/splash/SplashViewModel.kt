package com.backbase.ui.splash

import android.app.Application
import androidx.lifecycle.*
import com.backbase.R
import com.backbase.data.model.CityModel
import com.backbase.data.repository.DataRepository
import com.backbase.domain.model.CityDomainModel
import com.backbase.ui.splash.model.SplashViewState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException

class SplashViewModel(
    app: Application,
    private val gson: Gson,
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

    private suspend fun getJsonFile() = withContext(Dispatchers.Default) {
        val jsonString: String? = try {
            getApplication<Application>().resources?.openRawResource(R.raw.cities)?.bufferedReader()
                .use { it?.readText() }
        } catch (ioException: IOException) {
            Timber.e("Get Json file from raw failed!")
            null
        }
        if (jsonString == null) {
            splashLiveData.postValue(SplashViewState.Error)
        } else {
            parseList(jsonString)
        }
    }

    private suspend fun parseList(rawData: String) = withContext(Dispatchers.Default) {
        val typeToken = object : TypeToken<List<CityModel>>() {}.type
        val data: List<CityModel> = try {
            gson.fromJson(rawData, typeToken)
        } catch (e: Exception) {
            Timber.e("Map Json error!")
            emptyList()
        }
        val mappedCities = data.map {
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
        splashLiveData.postValue(
            if (data.isEmpty()) {
                SplashViewState.Error
            } else {
                SplashViewState.Success
            }
        )
    }
}

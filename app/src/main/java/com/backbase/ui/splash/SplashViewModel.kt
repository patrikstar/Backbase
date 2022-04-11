package com.backbase.ui.splash

import androidx.lifecycle.*
import com.backbase.domain.repository.DomainRepository
import com.backbase.ui.splash.model.SplashViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(
    private val domainRepository: DomainRepository
) : ViewModel() {

    private val _splashLiveData = MutableLiveData<SplashViewState>()
    val splashLiveData: LiveData<SplashViewState> = _splashLiveData

    init {
        getCitiesFromRaw()
    }

    private fun getCitiesFromRaw() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.Default) {
                    _splashLiveData.postValue(
                        if (domainRepository.parseAndSaveData()) {
                            SplashViewState.Success
                        } else {
                            SplashViewState.Error
                        }
                    )
                }
            } catch (e: Exception) {
                _splashLiveData.postValue(SplashViewState.Error)
            }
        }
    }
}

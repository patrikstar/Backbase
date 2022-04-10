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

    val splashLiveData = MutableLiveData<SplashViewState>()

    init {
        getCitiesFromRaw()
    }

    private fun getCitiesFromRaw() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                splashLiveData.postValue(
                    if (domainRepository.parseAndSaveData()) {
                        SplashViewState.Success
                    } else {
                        SplashViewState.Error
                    }
                )
            }
        }
    }
}

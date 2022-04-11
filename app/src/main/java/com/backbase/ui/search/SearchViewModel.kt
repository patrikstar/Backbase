package com.backbase.ui.search

import androidx.lifecycle.*
import com.backbase.domain.repository.DomainRepository
import com.backbase.ui.search.model.ListViewState
import com.backbase.ui.search.model.MessageText
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

@OptIn(InternalCoroutinesApi::class)
class SearchViewModel(
    private val domainRepository: DomainRepository
) : ViewModel() {
    private val _listLiveData = MutableLiveData<ListViewState>()
    val listLiveData: LiveData<ListViewState> = _listLiveData

    private var searchJob: Job? = null

    init {
        _listLiveData.value = ListViewState.Loading
        viewModelScope.launch {
            try {
                domainRepository.fetchData()
                domainRepository.searchResultFlow().collect { list ->
                    if (list.isEmpty()) {
                        _listLiveData.postValue(ListViewState.Message(MessageText.EMPTY))
                    } else {
                        _listLiveData.postValue(ListViewState.Success(list))
                    }
                }
            } catch (e: Exception) {
                _listLiveData.postValue(ListViewState.Message(MessageText.ERROR))
            }
        }
    }

    fun onUserTyped(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            try {
                domainRepository.searchWithKey(query)
            } catch (e: Exception) {
                _listLiveData.postValue(ListViewState.Message(MessageText.ERROR))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
}

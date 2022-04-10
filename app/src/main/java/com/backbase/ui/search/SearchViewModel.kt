package com.backbase.ui.search

import androidx.lifecycle.*
import com.backbase.domain.repository.DomainRepository
import com.backbase.ui.search.model.ListViewState
import kotlinx.coroutines.launch

class SearchViewModel(
    private val domainRepository: DomainRepository
) : ViewModel() {
    val listLiveData = MutableLiveData<ListViewState>()
    private val searchQuery = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            listLiveData.postValue(ListViewState.Success(domainRepository.fetchData()))
        }
    }

    fun onUserTyped(query: String) {

    }
}

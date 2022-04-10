package com.backbase.ui.search

import androidx.lifecycle.*
import com.backbase.data.repository.DataRepository
import com.backbase.ui.search.model.ListViewState
import kotlinx.coroutines.launch

class SearchViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {
    val listLiveData = MutableLiveData<ListViewState>()
    private val searchQuery = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            listLiveData.postValue(ListViewState.Success(dataRepository.getInitialList()))
        }
    }
}

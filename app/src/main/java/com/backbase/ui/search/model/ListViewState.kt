package com.backbase.ui.search.model

import com.backbase.domain.model.CityDomainModel

sealed class ListViewState {
    object Loading : ListViewState()

    object Error : ListViewState()

    data class Success(
        val cities: List<CityDomainModel>
    ) : ListViewState()
}

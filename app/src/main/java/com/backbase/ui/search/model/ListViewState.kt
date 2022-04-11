package com.backbase.ui.search.model

import com.backbase.R
import com.backbase.domain.model.CityDomainModel

sealed class ListViewState {
    object Loading : ListViewState()

    data class Message(
        val message: MessageText
    ) : ListViewState()

    data class Success(
        val cities: List<CityDomainModel>
    ) : ListViewState()
}

enum class MessageText(val messageId: Int) {
    ERROR(R.string.something_went_wrong),
    EMPTY(R.string.empty_list)
}

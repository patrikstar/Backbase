package com.backbase.ui.navigation

import com.backbase.domain.model.CityDomainModel

sealed class NavPath {

    object GoToSearch : NavPath()

    class GoToMap(val city: CityDomainModel) : NavPath()
}

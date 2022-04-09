package com.backbase.ui.navigation

import com.backbase.domain.model.CityModel

sealed class NavPath {

    object GoToSearch : NavPath()

    class GoToMap(val city: CityModel) : NavPath()
}

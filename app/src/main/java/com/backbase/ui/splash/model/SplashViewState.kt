package com.backbase.ui.splash.model

sealed class SplashViewState {

    object Error : SplashViewState()

    object Success : SplashViewState()
}

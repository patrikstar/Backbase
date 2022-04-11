package com.backbase.ui.di

import com.backbase.ui.search.SearchViewModel
import com.backbase.ui.search.recycler.ListAdapter
import com.backbase.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    factory { ListAdapter() }
    viewModel { SplashViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}

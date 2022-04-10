package com.backbase.data.di

import com.backbase.data.DataRepositoryImpl
import com.backbase.data.repository.DataRepository
import org.koin.dsl.module

val dataModule = module {
    single<DataRepository> { DataRepositoryImpl() }
}

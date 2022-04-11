package com.backbase.data.di

import com.backbase.data.repository.DataRepositoryImpl
import com.backbase.data.mapper.CityDataToDomainMapper
import com.backbase.data.parser.AssetsJsonParserImpl
import com.backbase.domain.repository.DataRepository
import com.backbase.data.parser.AssetsJsonParser
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    factory { CityDataToDomainMapper() }
    single { Json { ignoreUnknownKeys = true } }
    single<AssetsJsonParser> { AssetsJsonParserImpl(get(), get()) }
    single<DataRepository> { DataRepositoryImpl(get(), get()) }
}

package com.backbase.data.di

import com.backbase.data.DataRepositoryImpl
import com.backbase.data.parser.JsonParserImpl
import com.backbase.data.repository.DataRepository
import com.backbase.data.parser.JsonParser
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single { Json { ignoreUnknownKeys = true } }
    single<JsonParser> { JsonParserImpl(get(), get()) }
    single<DataRepository> { DataRepositoryImpl(get()) }
}

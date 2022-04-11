package com.backbase.domain.di

import com.backbase.domain.DomainRepositoryImpl
import com.backbase.domain.repository.DomainRepository
import org.koin.dsl.module

val domainModule = module {
    factory<DomainRepository> { DomainRepositoryImpl(get()) }
}

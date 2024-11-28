package com.mtc.data.di

import com.mtc.data.repository.FestimateRepositoryImpl
import com.mtc.domain.repository.FestimateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsFestimateRepository(festimateRepositoryImpl: FestimateRepositoryImpl): FestimateRepository
}

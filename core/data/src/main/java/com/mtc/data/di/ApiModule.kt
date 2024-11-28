package com.mtc.data.di

import com.mtc.data.remote.api.FestimateApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun providesFestimateApi(retrofit: Retrofit): FestimateApi = retrofit.create()
}

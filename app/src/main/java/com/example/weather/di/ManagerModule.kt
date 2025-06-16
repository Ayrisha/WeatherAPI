package com.example.weather.di

import com.example.weather.data.manager.SharedPrefManagerImpl
import com.example.weather.domain.manager.SharedPrefManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {
    @Binds
    @Singleton
    abstract fun bindCityIdManger(sharedPrefManager: SharedPrefManagerImpl) : SharedPrefManager
}
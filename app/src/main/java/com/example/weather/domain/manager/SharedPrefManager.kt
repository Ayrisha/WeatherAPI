package com.example.weather.domain.manager

import kotlinx.coroutines.flow.Flow

interface SharedPrefManager {
    suspend fun saveCityData(id: Int?, name: String?)
    fun readCityData(): Flow<Pair<Int?, String?>>
    suspend fun removeCityData()
}
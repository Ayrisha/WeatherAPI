package com.example.weather.domain.usecases

import com.example.weather.domain.manager.SharedPrefManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadSharedPref @Inject constructor(
    private val sharedPrefManager: SharedPrefManager
) {

    operator fun invoke(): Flow<Pair<Int?, String?>> {
        return sharedPrefManager.readCityData()
    }

}
package com.example.weather.domain.usecases

import com.example.weather.domain.manager.SharedPrefManager
import javax.inject.Inject

class SaveSharedPref @Inject constructor(
    private val sharedPrefManager: SharedPrefManager
) {
    suspend operator fun invoke(id: Int?, name: String?) {
        sharedPrefManager.saveCityData(id, name)
    }
}
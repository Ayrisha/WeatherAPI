package com.example.weather.domain.usecases

import com.example.weather.domain.manager.SharedPrefManager
import javax.inject.Inject

class DeleteSharedPref @Inject constructor(
    private val sharedPrefManager: SharedPrefManager
) {
    suspend operator fun invoke() {
        sharedPrefManager.removeCityData()
    }
}
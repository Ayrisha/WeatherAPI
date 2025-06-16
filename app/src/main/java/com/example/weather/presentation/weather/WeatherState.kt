package com.example.weather.presentation.weather

import com.example.weather.domain.model.WeatherResponse

data class WeatherState(
    val weatherInfo: WeatherResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
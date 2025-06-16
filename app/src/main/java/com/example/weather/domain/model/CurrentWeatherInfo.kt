package com.example.weather.domain.model

data class CurrentWeatherInfo(
    val location: String?,
    val temperature: Double?,
    val icon: String?,
    val description: String?,
    val wind_speed: Double?,
    val humidity: Int?
)
package com.example.weather.domain.model

data class WeatherResponse(
    val current: CurrentWeatherInfo,
    val forecast: List<ForecastWeatherInfo>?
)
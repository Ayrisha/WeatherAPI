package com.example.weather.domain.usecases

import com.example.weather.domain.model.WeatherResponse
import com.example.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeather @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city:String): WeatherResponse {
        return repository.getWeather(city = city).toWeatherResponse()
    }
}
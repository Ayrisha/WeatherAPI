package com.example.weather.domain.usecases

import com.example.weather.domain.model.City
import com.example.weather.domain.repository.WeatherRepository

import javax.inject.Inject

class GetCityList @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city:String): List<City> {
        return repository.getListCities(city = city).map {
            it.toCity()
        }
    }
}
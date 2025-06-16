package com.example.weather.domain.repository

import com.example.weather.data.network.dto.cityList.CityDto
import com.example.weather.data.network.dto.weather.WeatherInfoDto
import com.example.weather.domain.model.City
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getListCities(city: String): List<CityDto>

    suspend fun getWeather(city: String): WeatherInfoDto

    fun getAllCity(): Flow<List<City>>

    suspend fun upsertCity(city: City)

    suspend fun deleteCity(city: City)

    suspend fun getCity(id: Int): Flow<City?>
}
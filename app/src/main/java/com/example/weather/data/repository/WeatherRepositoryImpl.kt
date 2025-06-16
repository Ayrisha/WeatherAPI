package com.example.weather.data.repository

import com.example.weather.data.local.CityDao
import com.example.weather.data.network.WeatherApi
import com.example.weather.data.network.dto.cityList.CityDto
import com.example.weather.data.network.dto.weather.WeatherInfoDto
import com.example.weather.domain.model.City
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val cityDao: CityDao
): WeatherRepository{
    override suspend fun getListCities(city: String): List<CityDto> {
        return weatherApi.getCities(city = city)
    }

    override suspend fun getWeather(city: String): WeatherInfoDto {
        return weatherApi.getWeather(city = city)
    }

    override fun getAllCity(): Flow<List<City>> {
        return cityDao.getCityList()
    }

    override suspend fun upsertCity(city: City) {
        cityDao.upsert(city)
    }

    override suspend fun deleteCity(city: City) {
        cityDao.delete(city)
    }

    override suspend fun getCity(id: Int): Flow<City?> {
        return cityDao.getCityById(id)
    }
}
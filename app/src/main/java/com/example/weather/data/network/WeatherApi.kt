package com.example.weather.data.network

import com.example.weather.data.network.dto.cityList.CityDto
import com.example.weather.data.network.dto.weather.WeatherInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("search.json")
    suspend fun getCities(
        @Query("q")city: String,
        @Query("key")key: String = "a739c7a822424abfb1e174449251406",
        @Query("lang")lang: String = "RU"): List<CityDto>

    @GET("forecast.json?&days=5&aqi=no&alerts=no")
    suspend fun getWeather(
        @Query("q")city: String,
        @Query("key")key: String = "a739c7a822424abfb1e174449251406",
        @Query("lang")lang: String = "RU",): WeatherInfoDto
}
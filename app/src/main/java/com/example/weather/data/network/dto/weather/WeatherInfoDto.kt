package com.example.weather.data.network.dto.weather

import com.example.weather.domain.model.CurrentWeatherInfo
import com.example.weather.domain.model.ForecastWeatherInfo
import com.example.weather.domain.model.WeatherResponse
import com.google.gson.annotations.SerializedName


data class WeatherInfoDto (

  @SerializedName("location" ) var location : Location? = Location(),
  @SerializedName("current"  ) var current  : Current?  = Current(),
  @SerializedName("forecast" ) var forecast : Forecast? = Forecast()

){
  fun toWeatherResponse(): WeatherResponse {
    val currentWeather = CurrentWeatherInfo(
      location = location?.name,
      temperature = current?.tempC,
      icon = current?.condition?.icon,
      description = current?.condition?.text,
      wind_speed = current?.windKph,
      humidity = current?.humidity
    )

    val forecastList = forecast?.forecastday?.map { day ->
      ForecastWeatherInfo(
        data = day.date,
        temperature = day.day?.avgtempC,
        icon = day.day?.condition?.icon
      )
    }

    return WeatherResponse(
      current = currentWeather,
      forecast = forecastList
    )
  }
}
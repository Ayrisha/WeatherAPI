package com.example.weather.data.network.dto.weather

import com.google.gson.annotations.SerializedName


data class Forecast (

  @SerializedName("forecastday" ) var forecastday : ArrayList<Forecastday> = arrayListOf()

)
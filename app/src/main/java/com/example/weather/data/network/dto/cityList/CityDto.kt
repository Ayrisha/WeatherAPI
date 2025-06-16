package com.example.weather.data.network.dto.cityList

import com.example.weather.domain.model.City
import com.google.gson.annotations.SerializedName

data class CityDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("region") val region: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("lat") val lat: Double? = null,
    @SerializedName("lon") val lon: Double? = null,
    @SerializedName("url") val url: String? = null
) {
    fun toCity(): City {
        return City(
            id = id,
            name = name,
            region = region
        )
    }
}

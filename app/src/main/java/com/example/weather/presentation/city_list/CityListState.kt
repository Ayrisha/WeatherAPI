package com.example.weather.presentation.city_list

import com.example.weather.domain.model.City

data class CityListState(
    val cities: List<City> = emptyList(),
    val citiesSaved: List<City> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val query: String = ""
)

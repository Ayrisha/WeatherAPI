package com.example.weather.domain.usecases

import com.example.weather.data.local.CityDao
import com.example.weather.domain.model.City
import javax.inject.Inject

class UpsertCity  @Inject constructor(
    private val cityDao: CityDao
) {

    suspend operator fun invoke(city: City){
        cityDao.upsert(city = city)
    }

}
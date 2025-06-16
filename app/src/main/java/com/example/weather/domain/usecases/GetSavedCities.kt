package com.example.weather.domain.usecases

import com.example.weather.data.local.CityDao
import com.example.weather.domain.model.City
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedCities  @Inject constructor(
    private val cityDao: CityDao
) {

    operator fun invoke(): Flow<List<City>>{
        return cityDao.getCityList()
    }

}
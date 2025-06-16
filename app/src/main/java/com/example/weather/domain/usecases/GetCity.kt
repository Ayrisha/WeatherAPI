package com.example.weather.domain.usecases

import com.example.weather.data.local.CityDao
import com.example.weather.domain.model.City
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCity @Inject constructor(
    private val cityDao: CityDao
) {

    operator fun invoke(id: Int): Flow<City?>{
        return cityDao.getCityById(id)
    }

}
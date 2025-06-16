package com.example.weather.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.weather.domain.model.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert
    suspend fun upsert(city: City)

    @Delete
    suspend fun delete(city: City)

    @Query("SELECT * FROM City")
    fun getCityList(): Flow<List<City>>

    @Query("SELECT * FROM City WHERE id = :id")
    fun getCityById(id: Int): Flow<City?>

}
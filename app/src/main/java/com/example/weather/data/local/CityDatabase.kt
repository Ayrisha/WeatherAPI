package com.example.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.domain.model.City

@Database(entities = [City::class],version = 1,)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
package com.example.weather.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey val id: Int?,
    val name: String?,
    val region: String?
)

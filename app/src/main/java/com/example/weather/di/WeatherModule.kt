package com.example.weather.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.weather.data.local.CityDao
import com.example.weather.data.local.CityDatabase
import com.example.weather.data.network.WeatherApi
import com.example.weather.data.repository.WeatherRepositoryImpl
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {
    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        val logging = HttpLoggingInterceptor { message ->
            Log.d("Retrofit", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .connectionPool(ConnectionPool(0, 1, TimeUnit.MINUTES))
            .addNetworkInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherApi, api2: CityDao): WeatherRepository{
        return WeatherRepositoryImpl(weatherApi = api, cityDao = api2)
    }

    @Provides
    @Singleton
    fun provideCityDatabase(@ApplicationContext context: Context): CityDatabase {
        return Room.databaseBuilder(
            context,
            CityDatabase::class.java,
            "weather-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCityDao(database: CityDatabase): CityDao {
        return database.cityDao()
    }

}
package com.example.weather.presentation.weather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.model.City
import com.example.weather.domain.usecases.GetCity
import com.example.weather.domain.usecases.GetWeather
import com.example.weather.domain.usecases.SaveSharedPref
import com.example.weather.domain.usecases.UpsertCity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel  @Inject constructor(
    private val getWeather: GetWeather,
    private val upsertCityUseCase: UpsertCity,
    private val getCity: GetCity,
    private val saveSharedPref: SaveSharedPref,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(WeatherState())
    val state: State<WeatherState> get() = _state

    init {
        val city: String? = savedStateHandle.get<String>("city") ?: savedStateHandle.get<String>("cityParam")
        if (!city.isNullOrBlank()) loadWeather(city)
    }

    fun onSaveCityId(id: Int?, name: String?) {
        viewModelScope.launch {
            saveSharedPref(id, name)
        }
    }

    fun saveCurrentCity( id: Int?, cityToSaved: City) {
        viewModelScope.launch {
            val city = id?.let { getCity(it) }?.firstOrNull()
            if (city == null){
                upsertCityUseCase(cityToSaved)
            }
        }
    }

    fun loadWeather(city: String?) {
        if (city.isNullOrBlank()) return
        viewModelScope.launch {
            try {
                val info = getWeather(city)
                _state.value = state.value.copy(
                    weatherInfo = info,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    error = e.message ?: "Ошибка",
                    isLoading = false
                )
            }
        }
    }
}

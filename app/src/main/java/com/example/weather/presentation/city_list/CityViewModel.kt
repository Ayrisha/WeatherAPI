package com.example.weather.presentation.city_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.usecases.GetCityList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State
import com.example.weather.domain.model.City
import com.example.weather.domain.usecases.DeleteCity
import com.example.weather.domain.usecases.DeleteSharedPref
import com.example.weather.domain.usecases.GetSavedCities
import com.example.weather.domain.usecases.ReadSharedPref
import kotlinx.coroutines.flow.firstOrNull


@HiltViewModel
class CityViewModel @Inject constructor(
    private val getCityList: GetCityList,
    private val getSavedCities: GetSavedCities,
    private val deleteCityUserCase: DeleteCity,
    private val deleteSharadPref: DeleteSharedPref,
    private val getSharadPref: ReadSharedPref
) : ViewModel() {

    private val _state = mutableStateOf(CityListState())
    val state: State<CityListState> get() = _state

    init {
        getCityList()
    }

    private fun getCityList() {
        viewModelScope.launch {
            getSavedCities().collect { item ->
                _state.value = _state.value.copy(citiesSaved = item)
            }
        }
    }


    fun deleteCity(city: City) {
        viewModelScope.launch {
            deleteCityUserCase(city)

            val (savedId, _) = getSharadPref().firstOrNull() ?: (null to null)
            if (savedId == city.id) {
                deleteSharadPref()
            }

            getCityList()
        }
    }

    fun onSearchQueryChanged(query: String) {
        if (query.isEmpty()){
            _state.value = _state.value.copy(query = query, cities = emptyList())
        }
        else{
            _state.value = _state.value.copy(query = query)
        }
    }

    fun loadCities(city: String) {

        if (_state.value.query.isEmpty()) {
            getCityList()
            return
        }

        viewModelScope.launch {

            if (_state.value.cities.isEmpty()){
                _state.value = _state.value.copy(isLoading = true)
            } else {
                val cities = getCityList(city)
                _state.value = _state.value.copy(
                    citiesSaved = cities,
                    isLoading = false,
                    error = null
                )
            }

            try {
                val cities = getCityList(city)
                _state.value = _state.value.copy(
                    cities = cities,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message ?: "Ошибка",
                    isLoading = false
                )
            }
        }
    }
}
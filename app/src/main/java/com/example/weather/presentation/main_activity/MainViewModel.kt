package com.example.weather.presentation.main_activity

import androidx.lifecycle.ViewModel
import com.example.weather.domain.usecases.ReadSharedPref
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.weather.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readSharedPref: ReadSharedPref
): ViewModel() {
    private val _id = mutableIntStateOf(-1)
    val id: State<Int> = _id

    private val _cityName = mutableStateOf<String?>(null)
    val cityName: State<String?> = _cityName

    val startDestination: String
        get() = if (_id.intValue != -1) {
            Route.WeatherNavigatorScreen.route
        } else {
            Route.CityNavigatorScreen.route
        }

    init {
        viewModelScope.launch {
            readSharedPref().collect { (id, name) ->
                _id.intValue = id ?: -1
                _cityName.value = name
            }
        }
    }
}
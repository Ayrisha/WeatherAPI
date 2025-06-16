package com.example.weather.presentation.weather

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.weather.domain.model.City
import com.example.weather.presentation.weather.component.FiveDayForecast
import com.example.weather.presentation.weather.component.TopLeftRightButtons


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel,
    city: String?,
    id: Int?,
    region: String?
) {
    val state = weatherViewModel.state.value


    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {

        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error != null -> {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                state.weatherInfo?.current.let { info ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TopLeftRightButtons(
                            onLeftClick = {
                                val cityToSave =
                                    City(
                                        id = id,
                                        name = city,
                                        region = region
                                    )
                                weatherViewModel.saveCurrentCity(id, cityToSave)
                                weatherViewModel.onSaveCityId(id, city)},
                            onRightClick = {
                                val cityToSave =
                                    City(
                                        id = id,
                                        name = city,
                                        region = region
                                    )
                                weatherViewModel.saveCurrentCity(id, cityToSave)
                            }
                        )

                        Text(
                            text = city.toString(),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Text(
                            text = "${info?.temperature ?: "0"}°C",
                            style = MaterialTheme.typography.displayLarge,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            if (info?.icon != null) {
                                AsyncImage(
                                    model = if (info.icon.startsWith("http")) {
                                        info.icon
                                    } else {
                                        "https:${info.icon}"
                                    },
                                    contentDescription = null,
                                    onLoading = { Log.d("AsyncImage", "Загрузка...") },
                                    onSuccess = { Log.d("AsyncImage", "Успешно загружено") },
                                    onError = { result -> Log.e("AsyncImage", "Ошибка загрузки $result") },
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            Text(
                                text = info?.description ?: "Нет информации",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                        Divider(modifier = Modifier.padding(bottom = 8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Ветер", style = MaterialTheme.typography.titleSmall)
                                Text(
                                    text = "${info?.wind_speed ?: 0} км/ч",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "Влажность",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    text = "${info?.humidity ?: 0}%",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                        state.weatherInfo?.forecast?.let {
                            FiveDayForecast(forecastList = it)
                        }
                    }
                }
            }
        }
    }
}


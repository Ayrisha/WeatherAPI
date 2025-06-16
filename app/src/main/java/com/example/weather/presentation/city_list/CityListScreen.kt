package com.example.weather.presentation.city_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weather.presentation.city_list.component.CityItem
import com.example.weather.presentation.city_list.component.CityItemSaved
import com.example.weather.presentation.navgraph.Route

@Composable
fun CityListScreen(
    viewModel: CityViewModel,
    navController: NavController
)
{
    val state = viewModel.state.value

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.fillMaxSize()) {

        TextField(
            value = state.query,
            onValueChange = { newQuery ->
                viewModel.onSearchQueryChanged(newQuery)
            },
            label = { Text("Введите название города") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    viewModel.loadCities(state.query)
                    keyboardController?.hide()
                }
            )
        )

        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            state.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Ошибка", color = MaterialTheme.colorScheme.error)
                }
            }
            state.cities.isEmpty() && state.query.isNotEmpty() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Город не найден")
                }
            }
            state.query.isEmpty() && state.citiesSaved.isNotEmpty() -> {
                Log.d("CityItemSaved", "LazyColumn")
                LazyColumn {
                    itemsIndexed(state.citiesSaved) { index, item ->
                        CityItemSaved(
                            city = item,
                            onItemClick = { city ->
                                city.id?.let {
                                    navController.navigate(Route.WeatherNavigatorScreen.createRoute(
                                        it, city.name.toString(), city.region.toString()
                                    ))
                                }
                            },
                            onItemDelete = {
                                viewModel.deleteCity(item)
                            })
                    }
                }
            }

            state.query.isEmpty() && state.citiesSaved.isEmpty() -> {
            }

            else -> {
                LazyColumn {
                    itemsIndexed(state.cities) { index, item ->
                        CityItem(
                            city = item,
                            onItemClick = { city ->
                                city.id?.let {
                                    navController.navigate(Route.WeatherNavigatorScreen.createRoute(
                                        it, city.name.toString(), city.region.toString()
                                    ))
                                }
                            })
                    }
                }
            }
        }
    }
}
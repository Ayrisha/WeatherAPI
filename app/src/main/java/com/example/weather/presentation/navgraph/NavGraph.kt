package com.example.weather.presentation.navgraph

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.presentation.city_list.CityListScreen
import com.example.weather.presentation.city_list.CityViewModel
import com.example.weather.presentation.weather.WeatherScreen
import com.example.weather.presentation.weather.WeatherViewModel

@Composable
fun NavGraph(
    startDestination: String,
    idSaved: Int,
    name: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Route.CityNavigatorScreen.route) {
            val viewModel: CityViewModel = hiltViewModel()

            CityListScreen(viewModel = viewModel, navController = navController)
        }

        composable(
            route = Route.WeatherNavigatorScreen.route,
            arguments = Route.WeatherNavigatorScreen.arguments
        ) { backStackEntry ->
            val argsId = backStackEntry.arguments?.getInt("id")
            val argsCity = backStackEntry.arguments?.getString("city")
            val argsRegion = backStackEntry.arguments?.getString("region")

            val (id, city, region) = remember(backStackEntry) {

                if (argsId == 0 && idSaved!= -1) {
                    Triple(idSaved, name, " ")
                } else {
                    Triple(argsId, argsCity, argsRegion)
                }
            }


            backStackEntry.savedStateHandle.apply {
                set("cityParam", city)
                set("idParam", id)
                set("regionParam", region)
            }


            BackHandler {
                navController.navigate(Route.CityNavigatorScreen.route) {
                    popUpTo(Route.WeatherNavigatorScreen.route) { inclusive = true }
                }
            }

            val weatherViewModel: WeatherViewModel = hiltViewModel()

            LaunchedEffect(weatherViewModel) {
                if (city != null && argsCity == null) {
                    weatherViewModel.loadWeather(city)
                }
            }

            WeatherScreen(weatherViewModel, id = id, city = city, region = region)
        }
    }
}

package com.example.weather.presentation.navgraph

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object CityNavigatorScreen : Route(route = "cityNavigator")

    object WeatherNavigatorScreen : Route(
        route = "weatherNavigator/{id}/{city}/{region}",
        arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
            },
            navArgument("city") {
                type = NavType.StringType
            },
            navArgument("region") {
                type = NavType.StringType
            }
        )
    ) {
        fun createRoute(id: Int?, city: String, region: String): String {
            return "weatherNavigator/$id/$city/$region"
        }
    }
}
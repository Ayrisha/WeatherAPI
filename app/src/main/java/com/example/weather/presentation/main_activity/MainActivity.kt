package com.example.weather.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.presentation.navgraph.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val idSaved = viewModel.id.value
            val name = viewModel.cityName.value

            val startDestination = viewModel.startDestination

                Box(
                    modifier = Modifier.fillMaxSize()) {
                    NavGraph(startDestination = startDestination, idSaved = idSaved, name = name.toString())
                }
        }
    }
}
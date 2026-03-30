package com.example.a25_09_b3cdev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a25_09_b3cdev.presentation.ui.screens.SearchScreen
import com.example.a25_09_b3cdev.presentation.ui.screens.WeatherDetailsScreen
import com.example.a25_09_b3cdev.presentation.ui.theme.AppTheme
import com.example.a25_09_b3cdev.presentation.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                val mainViewModel: MainViewModel = viewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = "main"
                        ) {
                            composable("main") {
                                SearchScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    mainViewModel = mainViewModel,
                                    onWeatherClick = { weather ->
                                        mainViewModel.setSelectedWeather(weather)
                                        navController.navigate("details")
                                    }
                                )
                            }
                            composable("details") {
                                WeatherDetailsScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    mainViewModel = mainViewModel,
                                    onBack = { navController.popBackStack() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


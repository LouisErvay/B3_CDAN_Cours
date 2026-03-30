package com.example.a25_09_b3cdev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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

                val backStackEntry = navController.currentBackStackEntryAsState().value
                val currentRoute = backStackEntry?.destination?.route
                val selected = mainViewModel.selectedWeather.value

                @OptIn(ExperimentalMaterial3Api::class)
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text =
                                        when (currentRoute) {
                                            "details" ->
                                                selected?.name
                                                    ?: stringResource(R.string.details_title_fallback)
                                            else -> stringResource(R.string.main_title)
                                        }
                                )
                            },
                            navigationIcon = {
                                if (currentRoute == "details") {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = stringResource(R.string.back)
                                        )
                                    }
                                }
                            }
                        )
                    }
                ) { innerPadding ->
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


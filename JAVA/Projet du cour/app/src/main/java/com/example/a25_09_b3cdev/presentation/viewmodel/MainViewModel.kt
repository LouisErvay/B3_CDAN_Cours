package com.example.a25_09_b3cdev.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a25_09_b3cdev.data.remote.DescriptionEntity
import com.example.a25_09_b3cdev.data.remote.KtorWeatherApi
import com.example.a25_09_b3cdev.data.remote.TempEntity
import com.example.a25_09_b3cdev.data.remote.WeatherEntity
import com.example.a25_09_b3cdev.data.remote.WindEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

suspend fun main() {
    val viewModel = MainViewModel()
    viewModel.loadWeathers("Paris")

    while (viewModel.runInProgress.value) {
        delay(500)
        println("List : ${viewModel.dataList.value}")
    }
    // Affichage de la liste (qui doit être remplie) contenue dans la donnée observable
    println("List : ${viewModel.dataList.value}")
    println("ErrorMessage : ${viewModel.errorMessage.value}")
    // Pour que le programme s'arrête, inutile sur Android
    KtorWeatherApi.close()
}

class MainViewModel : ViewModel() {
    // MutableStateFlow est une donnée observable
    val dataList = MutableStateFlow(emptyList<WeatherEntity>())
    val runInProgress = MutableStateFlow(false)
    val errorMessage = MutableStateFlow("")
    val selectedWeather = MutableStateFlow<WeatherEntity?>(null)

    // init { // Création d'un jeu de donnée au démarrage
    //     println("Instanciation de MainViewModel")
    //     loadFakeData()
    // }

    fun loadFakeData(runInProgress: Boolean = false, errorMessage: String = "") {
        this.runInProgress.value = runInProgress
        this.errorMessage.value = errorMessage
        dataList.value =
            listOf(
                WeatherEntity(
                    id = 1,
                    name = "Paris",
                    main = TempEntity(temp = 18.5),
                    weather =
                        listOf(
                            DescriptionEntity(
                                description = "ciel dégagé",
                                icon =
                                    "https://picsum.photos/200"
                            )
                        ),
                    wind = WindEntity(speed = 5.0)
                ),
                WeatherEntity(
                    id = 2,
                    name = "Toulouse",
                    main = TempEntity(temp = 22.3),
                    weather =
                        listOf(
                            DescriptionEntity(
                                description =
                                    "partiellement nuageux",
                                icon =
                                    "https://picsum.photos/201"
                            )
                        ),
                    wind = WindEntity(speed = 3.2)
                ),
                WeatherEntity(
                    id = 3,
                    name = "Toulon",
                    main = TempEntity(temp = 25.1),
                    weather =
                        listOf(
                            DescriptionEntity(
                                description = "ensoleillé",
                                icon =
                                    "https://picsum.photos/202"
                            )
                        ),
                    wind = WindEntity(speed = 6.7)
                ),
                WeatherEntity(
                    id = 4,
                    name = "Lyon",
                    main = TempEntity(temp = 19.8),
                    weather =
                        listOf(
                            DescriptionEntity(
                                description =
                                    "pluie légère",
                                icon =
                                    "https://picsum.photos/203"
                            )
                        ),
                    wind = WindEntity(speed = 4.5)
                )
            )
                .shuffled() // shuffled() pour avoir un ordre différent à chaque
        // appel
    }

    fun loadWeathers(cityName: String) {
        runInProgress.value = true
        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            try {

                if (cityName.length < 3) {
                    throw Exception(
                        "Le nom de la ville doit contenir au moins 3 caractères"
                    )
                }

                dataList.value = KtorWeatherApi.loadWeathers(cityName)
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.value = e.message ?: "Une erreur est survenue"
            } finally {
                runInProgress.value = false
            }
        }
    }

    fun setSelectedWeather(weather: WeatherEntity) {
        selectedWeather.value = weather
    }
}

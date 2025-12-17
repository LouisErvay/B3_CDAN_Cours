package com.example.course_project.exo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val runInProgress = MutableStateFlow(false)
    var weathers: List<WeatherBean> = emptyList()

    fun loadWeathers(ville: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runInProgress.value = true
            try {
                weathers = KtorWeatherAPI.loadWeathers(ville)
            } finally {
                runInProgress.value = false
            }
        }
    }
}

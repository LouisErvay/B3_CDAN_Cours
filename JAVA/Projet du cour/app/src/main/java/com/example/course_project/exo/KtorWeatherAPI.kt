package com.example.course_project.exo

import android.annotation.SuppressLint
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import java.io.PrintStream
import java.nio.charset.StandardCharsets
import java.util.Scanner
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

suspend fun main() {
    System.setOut(PrintStream(System.out, true, StandardCharsets.UTF_8))
    System.setErr(PrintStream(System.err, true, StandardCharsets.UTF_8))

    val scanner = Scanner(System.`in`, StandardCharsets.UTF_8.name())
    print("Entrez le nom de la ville : ")
    val city = scanner.nextLine()

    val weathers = KtorWeatherAPI.loadWeathers(city)
    weathers.forEach { weatherBean ->
        println(weatherBean.id)
        println(weatherBean.name)
        println(weatherBean.getResume())
        println()
    }
    KtorWeatherAPI.close()
}

object KtorWeatherAPI {
    private const val API_KEY = "b80967f0a6bd10d23e44848547b26550"
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/find"

    private val client = HttpClient {
        install(Logging) {
            logger =
                    object : Logger {
                        override fun log(message: String) {
                            println(message)
                        }
                    }
            level = LogLevel.INFO
        }
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
        }
        expectSuccess = true // Exception si code >= 300
    }

    suspend fun loadWeathers(ville: String): List<WeatherBean> {
        val response: WeatherResponse =
                client
                        .get(BASE_URL) {
                            parameter("q", ville)
                            parameter("appid", API_KEY)
                            parameter("units", "metric")
                            parameter("lang", "fr")
                        }
                        .body()
        return response.list
    }

    fun close() = client.close()
}

// DATA CLASS

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class WeatherResponse(val list: List<WeatherBean>)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class WeatherBean(
        val id: Int,
        val name: String,
        val main: MainBean,
        val wind: WindBean,
        val weather: List<WeatherInfoBean>
) {
    fun getResume(): String {
        val description = weather.firstOrNull()?.description ?: "N/A"
        val icon = weather.firstOrNull()?.icon ?: "N/A"
        return "Il fait ${main.temp}° à $name (id=$id) avec un vent de ${wind.speed} m/s\n" +
                "- Description : $description\n" +
                "- Icône : $icon"
    }
}

@SuppressLint("UnsafeOptInUsageError") @Serializable data class MainBean(val temp: Double)

@SuppressLint("UnsafeOptInUsageError") @Serializable data class WindBean(val speed: Double)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class WeatherInfoBean(val description: String, val icon: String)

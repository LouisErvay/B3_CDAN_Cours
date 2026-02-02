package com.example.a25_09_b3cdev

import com.example.a25_09_b3cdev.data.remote.KtorMuseumApi
import com.example.a25_09_b3cdev.data.remote.KtorUserApi
import com.example.a25_09_b3cdev.data.remote.KtorWeatherApi
import com.example.a25_09_b3cdev.data.remote.MuseumObject
import com.example.a25_09_b3cdev.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class TestAPI {

    @Test
    fun testMainViewModel() = runBlocking {
        val viewModel = MainViewModel()

        viewModel.loadWeathers("Nice")
        //Affichage de la liste (qui doit être remplie) contenue dans la donnée observable
        println("List : ${viewModel.dataList.value}")
    }

    @Test
    fun testKtorWeatherAPI() = runBlocking {

        val res = KtorWeatherApi.loadWeathers("Nice")
        for (r in res) {
            println(
                """
            Il fait ${r.main.temp}° à ${r.name} (id=${r.id}) avec un vent de ${r.wind.speed} m/s
            -Description : ${r.weather.firstOrNull()?.description ?: "-"}
            -Icône : ${r.weather.firstOrNull()?.icon ?: "-"}
        """.trimIndent()
            )
        }
    }


    @Test
    fun testKtorMuseumAPI() = runBlocking {
        val list: List<MuseumObject> = KtorMuseumApi.getData()
        println(list.joinToString(separator = "\n\n"))
        KtorMuseumApi.close()
    }


    @Test
    fun testKtorUserAPI() = runBlocking {
        val user = KtorUserApi.getRandomUser()
        repeat(5) {
            println(
                """
        Il s'appelle ${user.name} pour le contacter :
        Phone : ${user.coord?.phone ?: "-"}
        Mail : ${user.coord?.mail ?: "-"}
    """.trimIndent()
            )

            println(user)
        }
    }
}


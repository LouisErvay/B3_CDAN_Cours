package com.example.a25_09_b3cdev

import com.example.a25_09_b3cdev.data.remote.KtorWeatherApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TestAPI {

    @Test
    fun loadWeatherNiceTest() = runBlocking {
        val res = KtorWeatherApi.loadWeathers("Nice")

        // 1) Vérifier que la liste reçue contient au moins 1 élément
        assertTrue("La liste météo est vide pour la recherche 'Nice'", res.isNotEmpty())

        // 2) Que le nom contient "Nice"
        val niceWeather = res.firstOrNull { it.name.contains("Nice", ignoreCase = true) }
        assertNotNull("Aucun élément dont le nom contient 'Nice' n'a été trouvé", niceWeather)

        // 3) Que la température est bien comprise entre -40 et 60
        val temp = niceWeather!!.main.temp
        assertTrue(
                "Température inattendue pour Nice: $temp (attendu entre -40 et 60)",
                temp in -40.0..60.0
        )

        // 4) Que la description contient au moins 1 élément avec une icône non vide
        val hasAtLeastOneNonEmptyIcon =
                niceWeather.weather.any { it.icon.isNotBlank() } ||
                        res.any { w -> w.weather.any { d -> d.icon.isNotBlank() } }

        assertTrue(
                "Aucune icône non vide trouvée dans les descriptions météo",
                hasAtLeastOneNonEmptyIcon
        )
    }
}


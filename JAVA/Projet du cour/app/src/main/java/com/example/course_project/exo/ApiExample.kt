package com.example.course_project.exo

import android.annotation.SuppressLint
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

//Suspend sera expliqué dans le chapitre des coroutines
suspend fun main() {
    val list : List<MuseumObject> = KtorMuseumApi.getData()
    println(list.joinToString(separator = "\n\n"))
    KtorMuseumApi.close()
}

object KtorMuseumApi {
    private const val API_URL =
        "https://raw.githubusercontent.com/Kotlin/KMP-App-Template/main/list.json"

    //Création et réglage du client
    private val client  = HttpClient {
        install(Logging) {
            //(import io.ktor.client.plugins.logging.Logger)
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = LogLevel.INFO  // TRACE, HEADERS, BODY, etc.
        }
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
        }
        expectSuccess = true //Exception si code >= 300
        //engine { proxy = ProxyBuilder.http("monproxy:1234") }
    }

    //GET Le JSON reçu sera parser en List<MuseumObject>,
    //Crash si le JSON ne correspond pas
    suspend fun getData(): List<MuseumObject> {
        return client.get(API_URL){
//            headers {
//                append("Authorization", "Bearer YOUR_TOKEN")
//                append("Custom-Header", "CustomValue")
//            }
        }.body()
        //possibilité de typer le body
        //.body<List<MuseumObject>>()
    }

    //POST
    suspend fun postData(newObject: MuseumObject): MuseumObject {
        return client.post(API_URL){
            setBody(newObject)
        }.body()
    }

    //Ferme le Client mais celui ci ne sera plus utilisable. Uniquement pour le main
    fun close() = client.close()

    //Avancés : Exemple avec Flow
    //fun getDataFlow() = flow<List<MuseumObject>> {
    //    emit(client.get(API_URL).body())
    //}
}

//DATA CLASS

@SuppressLint("UnsafeOptInUsageError")
@Serializable //KotlinX impose cette annotation
data class MuseumObject(
    val objectID: Int,
    val title: String,
    val artistDisplayName: String,
    val primaryImage: String,
    //Si un attribut n'est pas toujours dans le JSON il faut lui donner une valeur par défaut
    val optionalField : String? = null
)
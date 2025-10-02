package com.example.course_project.exo

//Beans.kt
class TownBean(val city: String, val cp: String) {
    var country: String? = null
}
data class DataTownBean(val city: String, val cp: String) {
    var country: String? = null
}

fun main(){
    val city = TownBean("Toulouse", "31000")
    city.country = "toto"
    val city2 = TownBean("Toulouse", "31000")
    city2.country = "toto2"

    val dataCity = DataTownBean("Toulouse", "31000")
    dataCity.country = "toto"
    val dataCity2 = DataTownBean("Toulouse", "31000")
    dataCity2.country = "toto2"

    println("city=$city") //city=TownBean@123245
    println("dataCity=$dataCity")//dataCity=DataTownBean(city=Toulouse, cp=31000)

    println("== : ${city == city2}") //== : false
    println("equals : ${city.equals(city2)}")// equals : false
    println("=== : ${city === city2}") //=== : false

    println("data == : ${dataCity == dataCity2}") // data == : true
    println("data equals : ${dataCity.equals(dataCity2)}")//data equals : true
    println("data === : ${dataCity === dataCity2}") //data === : false
}
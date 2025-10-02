package com.example.course_project.exo.model

import java.util.Random

class CarBean(val marque: String = "", val modele: String = "", val annee: Int = 0){
    var color: String = ""
}

class HouseBean(var color: String, var width: Int, var lenght : Int){
    var aera = width * lenght

    fun print() = println("La maison $color a une aire de $aera")
}

class PrintRandomIntBean(val max: Int = 100){
    private val random = Random()

    init {
        println("Le nombre aléatoire est ${random.nextInt(max)}")
    }
}


//Beans.kt value en paramètre  pour pouvoir surcharger son setter
class ThermometerBeanV2(val min:Int, val max:Int, value: Int) {
    var value = value //ne déclenche pas le setter
        set(value) {
            field = value
            if(field < min) {
                field = min
            }
            else if(field  > max) {
                field = max
            }
        }
    init  {
        this.value = value // déclenche le setter à l'initialisation
    }
}
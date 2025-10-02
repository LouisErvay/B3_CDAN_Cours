package com.example.course_project.exo

import com.example.course_project.exo.PRIX_BAGUETTE
import com.example.course_project.exo.PRIX_CROISSANT
import com.example.course_project.exo.PRIX_SANDWICHE

import com.example.course_project.exo.model.CarBean

fun boulangerie(nb_croissants: Int = 0, nb_baguettes: Int = 0, nb_sandwiches: Int = 0)
= (nb_croissants * PRIX_CROISSANT) + (nb_baguettes * PRIX_BAGUETTE) + (nb_sandwiches * PRIX_SANDWICHE)

fun main(){
    var voiture = CarBean("Seat", "Lean", 2022)
    voiture.color = "Grise"

    println("C'est une voiture ${voiture.marque} ${voiture.modele} de couleur ${voiture.color} et de l'année ${voiture.annee}")
}
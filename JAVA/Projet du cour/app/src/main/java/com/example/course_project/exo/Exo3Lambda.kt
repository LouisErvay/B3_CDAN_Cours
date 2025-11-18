package com.example.course_project.exo

data class PersonBean(val nom: String, var note: Double)

// Jeu de données en dur
private val LISTE_PERSONNES =
        listOf(
                PersonBean("Toto", 5.0),
                PersonBean("Bobby", 0.0),
                PersonBean("Toto", 12.0),
                PersonBean("George", 5.0),
                PersonBean("Bob", 14.0),
                PersonBean("John", 14.0),
                PersonBean("Toto", 8.0),
                PersonBean("Alice", 15.0),
                PersonBean("Toto", 11.0),
                PersonBean("Charlie", 9.0)
        )

fun main() {
    exo3()
}

fun exo3() {
    var personnes = LISTE_PERSONNES.toMutableList()

    // Lambda pour tester si une personne s'appelle Toto
    val isToto: (PersonBean) -> Boolean = { it.nom == "Toto" }

    println("=== Exercice 3 : Lambdas ===\n")

    // 1. Afficher la sous liste de personne ayant 10 et + (filter)
    println("1. Personnes ayant la moyenne (>= 10) :")
    val avecMoyenne = personnes.filter { it.note >= 10 }
    avecMoyenne.forEach { println("  ${it.nom} : ${it.note}") }
    println()

    // 2. Afficher combien il y a de Toto dans la classe ? (count)
    val nbToto = personnes.count(isToto)
    println("2. Nombre de Toto dans la classe : $nbToto")
    println()

    // 3. Afficher combien de Toto ayant la moyenne (10 et +)
    val nbTotoAvecMoyenne = personnes.count { isToto(it) && it.note >= 10 }
    println("3. Nombre de Toto ayant la moyenne (>= 10) : $nbTotoAvecMoyenne")
    println()

    // 4. Afficher combien de Toto ont plus que la moyenne de la classe (map + average)
    val moyenneClasse = personnes.map { it.note }.average()
    val nbTotoPlusQueMoyenne = personnes.count { isToto(it) && it.note > moyenneClasse }
    println("4. Moyenne de la classe : ${String.format("%.2f", moyenneClasse)}")
    println("   Nombre de Toto ayant plus que la moyenne : $nbTotoPlusQueMoyenne")
    println()

    // 5. Afficher les noms sans doublon (distinct) par ordre alphabétique
    println("5. Noms sans doublon par ordre alphabétique :")
    val nomsDistincts = personnes.map { it.nom }.distinct().sorted()
    nomsDistincts.forEach { println("  $it") }
    println()

    // 6. Ajouter un point à ceux n'ayant pas la moyenne (<10)
    println("6. Ajout d'un point à ceux n'ayant pas la moyenne (<10) :")
    personnes.filter { it.note < 10 }.forEach { it.note += 1.0 }
    personnes.forEach { println("  ${it.nom} : ${it.note}") }
    println()

    // 7. Ajouter un point à tous les Toto
    println("7. Ajout d'un point à tous les Toto :")
    personnes.filter(isToto).forEach { it.note += 1.0 }
    personnes.forEach { println("  ${it.nom} : ${it.note}") }
    println()

    // 8. Retirer de la liste (removeIf) ceux ayant la note la plus petite
    println("8. Retrait de ceux ayant la note la plus petite :")
    val noteMin = personnes.minOfOrNull { it.note } ?: 0.0
    println("   Note minimale : $noteMin")
    personnes.removeIf { it.note == noteMin }
    personnes.forEach { println("  ${it.nom} : ${it.note}") }
    println()

    // 9. Afficher les noms de ceux ayant la moyenne(10et+) par ordre alphabétique
    println("9. Noms des personnes ayant la moyenne (>=10) par ordre alphabétique :")
    val nomsAvecMoyenne = personnes.filter { it.note >= 10 }.map { it.nom }.sorted()
    nomsAvecMoyenne.forEach { println("  $it") }
    println()

    // 10. Dupliquer la liste ainsi que tous les utilisateurs (nouvelle instance) qu'elle contient
    // (map + copy)
    println("10. Duplication de la liste et de tous les utilisateurs :")
    val listeDupliquee = personnes.map { it.copy() }
    println("   Liste originale : ${personnes.size} personnes")
    println("   Liste dupliquée : ${listeDupliquee.size} personnes")
    println("   Modification d'une note dans la liste dupliquée pour vérifier l'indépendance :")
    if (listeDupliquee.isNotEmpty()) {
        listeDupliquee[0].note = 99.0
        println("   Originale[0] : ${personnes[0].nom} = ${personnes[0].note}")
        println("   Dupliquée[0] : ${listeDupliquee[0].nom} = ${listeDupliquee[0].note}")
    }
    println()

    // Bonus : Afficher par notes croissantes, les personnes ayant eu cette note
    println("=== BONUS : Affichage par notes croissantes ===")
    val personnesParNote = personnes.groupBy { it.note }.toSortedMap()
    personnesParNote.forEach { (note, liste) ->
        val noms = liste.joinToString(", ") { it.nom }
        println("$note : $noms")
    }
}

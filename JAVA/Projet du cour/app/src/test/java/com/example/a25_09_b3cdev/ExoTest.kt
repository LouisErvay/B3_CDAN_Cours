package com.example.a25_09_b3cdev

import com.example.a25_09_b3cdev.data.remote.WindEntity
import org.junit.Test


class MyLiveData<T>(value: T) {
    var value:T = value
        set(newValue) {
            field = newValue
            action(newValue)
        }

    var action : (T)->Unit = {}

}

class ExoTest {

    @Test
    fun main() {

        var toto = MyLiveData(WindEntity(12.5))

        toto.value = WindEntity(13.5)

        toto.action = {
            println(it)
        }

        toto.value = WindEntity(14.5)


    }



    fun exo1() {
        //Déclaration
        val lower: (String) -> Unit = { it: String -> println(it.lowercase()) }
        val lower2 = { it: String -> println(it.lowercase()) }
        val lower3: (String) -> Unit = { it -> println(it.lowercase()) }
        val lower4: (String) -> Unit = { println(it.lowercase()) }

        val hour: (Int) -> Int = { it / 60 }
        val max: (Int, Int) -> Int = { a, b -> Math.max(a, b) }
        val reverse: (String) -> String = { it.reversed() }

        var minToMinHour: ((Int?) -> Pair<Int, Int>?)? = {
            if (it == null) null else Pair(it / 60, it % 60)
        }

        minToMinHour = null

        //Appel
        lower("Coucou")
        val res = hour(123)
        println(res)
        println(max(12, 5))
        println(minToMinHour?.invoke(125))
        println(minToMinHour?.invoke(null))
    }

}


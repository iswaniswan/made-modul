package com.dicoding.tourismapp

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.junit.Test

class MainActivityTest {

    @Test
    fun Test() {
        println("TEST")
    }

    @Test
    fun testFlow() {
        GlobalScope.launch {
            makeFlow().collect {
                println("dapat nilai $it")
            }
        }
        println("flow finish")
    }

    fun makeFlow() = flow {
        println("mengirimkan nilai pertama")
        emit(1)
        println("mengirimkan nilai kedua")
        emit(2)
        println("mengirimkan nilai ketiga")
        emit(3)
        println("selesai")
    }

}
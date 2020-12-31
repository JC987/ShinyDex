package com.orangeanchorapps.shinydex.classes

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ShinyHuntTest {
    lateinit var hunt: ShinyHunt
    //TODO: add unit test
    @Before
    fun setUp() {
        val p = Pokemon()
        hunt = ShinyHunt(p,0, false)
    }

    @After
    fun tearDown() {
    }


    //Purpose: to test changing encounters with adding
    @Test
    fun test1(){
        hunt.changeEncounters(true)
        hunt.changeEncounters(true)
        assertEquals(2,hunt.encounters)
    }
    //Purpose: to test changing encounters with adding and removing
    @Test
    fun test2(){
        hunt.changeEncounters(true)
        hunt.changeEncounters(true)
        hunt.changeEncounters(false)
        assertEquals(1,hunt.encounters)
    }
    //Purpose: to test subtracting more times than encounters
    @Test
    fun test3(){
        hunt.changeEncounters(false)
        hunt.changeEncounters(false)
        assertEquals(0,hunt.encounters)
    }

    //Purpose: test changing the encounters of a completed hunt
    @Test
    fun test4(){
        hunt.changeEncounters(true)
        hunt.isCompleted = true
        hunt.changeEncounters(true)
        assertEquals(1,hunt.encounters)
    }
}
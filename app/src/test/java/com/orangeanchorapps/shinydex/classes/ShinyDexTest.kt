package com.orangeanchorapps.shinydex.classes

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

//TODO: add unit test
class ShinyDexTest {
    val dex = ShinyDex()
    lateinit var p: Pokemon
    lateinit var h: ShinyHunt
    @Before
    fun setUp() {
        p = Pokemon("Squirtle")
        h = ShinyHunt(p,0,false)

    }

    @After
    fun tearDown() {
    }
    //Purpose: to test size fun
    @Test
    fun test1(){
        dex.addHunt(h)
        assertEquals(1,dex.size())
    }

    //Purpose: to ensure that the item is added in the correct pos
    @Test
    fun test2(){
        dex.addHunt(h)
        assertEquals(h,dex.getHunt(0))
    }

    //Purpose: test removing an item that exists
    @Test
    fun test3(){
        dex.addHunt(h)
        assertEquals(true,dex.remove(0))
    }
    //Purpose: test removing an item at pos > size
    @Test
    fun test4(){
        assertEquals(false,dex.remove(2))
    }
    //Purpose: test removing an item at pos < 0
    @Test
    fun test5(){
        assertEquals(false,dex.remove(-1))
    }
}
package com.orangeanchorapps.shinydex.classes

import android.graphics.BitmapFactory
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.orangeanchorapps.shinydex.MainActivity
import com.orangeanchorapps.shinydex.R
import junit.framework.Assert.assertEquals

//@RunWith(RobolectricTestRunner::class)
class PokemonTest {
  /*  //TODO: add unit test

    lateinit var pokemon:Pokemon
    @Before
    fun setUp() {
        //set up pokemon
        val c = getApplicationContext<MainActivity>()
        val b = BitmapFactory.decodeResource(c.resources, R.drawable.shiny_squirtle_api)
        val p = Pokemon("name", b)
        pokemon = p
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test1(){
        assertEquals("name",pokemon.name)
    }*/

    //Purpose: to test if the sprite is null
    @Test
    fun test1(){
        val p = Pokemon()
        assertEquals(false,p.hasImage())
    }

    //Purpose: to test if we havn't fetched data
    @Test
    fun test2(){
        val p = Pokemon()
        assertEquals(false,p.isLoaded())
    }

    //Purpose: to test if we have fetched data
    @Test
    fun test3(){
        val p = Pokemon()
        p.name = "Squirtle"
        //If we have fetched the name then we have fetch the data from API
        assertEquals(true,p.isLoaded())
    }
}
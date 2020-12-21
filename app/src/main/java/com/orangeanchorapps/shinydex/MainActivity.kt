package com.orangeanchorapps.shinydex

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.orangeanchorapps.shinydex.classes.Pokemon
import com.orangeanchorapps.shinydex.classes.ShinyDex
import com.orangeanchorapps.shinydex.classes.ShinyHunt

class MainActivity : AppCompatActivity() {

    companion object{
        val dex = ShinyDex()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_active_hunts, R.id.navigation_shiny_dex, R.id.navigation_new_hunt, R.id.navigation_shiny_pokemon))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        dex.addHunt(ShinyHunt(Pokemon("Tangela"),162,true))
        dex.addHunt(ShinyHunt(Pokemon("Squirtle"),312,false))

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}
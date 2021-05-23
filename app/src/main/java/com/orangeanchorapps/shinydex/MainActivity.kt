package com.orangeanchorapps.shinydex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btmNavView = findViewById<BottomNavigationView>(R.id.bottomNavView)
        //navigation controller
        val navController = findNavController(R.id.fragment)

        //allows the action bar to change title with fragments
        val actionBarController = AppBarConfiguration(setOf(R.id.activeHuntFragment, R.id.completedHuntFragment, R.id.searchPokemonFragment))

        setupActionBarWithNavController(navController, actionBarController)

        //add nav contoller to nav view
        //so now if a view in bottom nav is clicked the fragments will change to the matching one
        btmNavView.setupWithNavController(navController)

    }

    // allows back button in actionbar to move back to previous fragment
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
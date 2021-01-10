package com.orangeanchorapps.shinydex


import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.orangeanchorapps.shinydex.classes.Pokemon
import com.orangeanchorapps.shinydex.classes.ShinyDex
import com.orangeanchorapps.shinydex.classes.ShinyHunt

class MainActivity : AppCompatActivity(), com.orangeanchorapps.shinydex.interfaces.Message {

    companion object{
        val dex = ShinyDex()

    }
    private var sab: ActionBar? = null

    override var messageInt: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_active_hunts, R.id.navigation_shiny_dex, R.id.navigation_new_hunt,
                 R.id.navigation_active_hunt_details, R.id.navigation_shiny_pokemon,R.id.navigation_search_pokemon))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        dex.addHunt(ShinyHunt(Pokemon("Tangela"),162,true))
        dex.addHunt(ShinyHunt(Pokemon("Tangela2"),12,true))
        dex.addHunt(ShinyHunt(Pokemon("Tangela3"),16,true))
        dex.addHunt(ShinyHunt(Pokemon("Tangela4"),62,true))

        val bitmap = BitmapFactory.decodeResource(resources,R.drawable.shiny_squirtle_api)

        dex.addHunt(ShinyHunt(Pokemon("Squirtle", bitmap),312,false))

        sab = supportActionBar
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("ShinyDex", "onOptionsItemSelected: ")
        when (item.itemId){
            android.R.id.home -> {
                Log.d("ShinyDex", "onOptionsItemSelected: home")
                supportFragmentManager.popBackStackImmediate()
            }

        }
        return super.onOptionsItemSelected(item)
    }*/

    fun showBackButton(){
        sab?.setDisplayHomeAsUpEnabled(true)
    }
    fun hideBackButton(){
        sab?.setDisplayHomeAsUpEnabled(false)
    }
    fun setTitle(s:String){
        sab?.title = s
    }

    override fun setMessage(i:Int) {
        messageInt = i
    }

    override fun receiveMessage(): Int {
        return messageInt
    }
}
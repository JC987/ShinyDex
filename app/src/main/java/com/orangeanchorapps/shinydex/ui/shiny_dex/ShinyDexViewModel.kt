package com.orangeanchorapps.shinydex.ui.shiny_dex


import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.*
import com.orangeanchorapps.shinydex.MainActivity
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.classes.Pokemon
import com.orangeanchorapps.shinydex.classes.PokemonShinyHunt
import com.orangeanchorapps.shinydex.classes.ShinyDex
import com.orangeanchorapps.shinydex.classes.ShinyHunt
import com.orangeanchorapps.shinydex.dao.PokemonDao
import com.orangeanchorapps.shinydex.dao.ShinyHuntDao
import com.orangeanchorapps.shinydex.database.PokemonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShinyDexViewModel(application: Application) : AndroidViewModel(application) {
    private val pokemonDao: PokemonDao
    private val shinyHuntDao: ShinyHuntDao
    private val bitmap:Bitmap
    //val x: LiveData<List<ShinyHunt>>
    //val y: LiveData<List<Pokemon>>
    var completedHunts: LiveData<List<PokemonShinyHunt>>

    init{
        pokemonDao = PokemonDatabase.getDatabase(application).PokemonDao()
        shinyHuntDao = PokemonDatabase.getDatabase(application).ShinyHuntDao()
        //x = shinyHuntDao.getAllShinyHunts()
        //y = pokemonDao.getAllPokemon()
        completedHunts = shinyHuntDao.getCompletedHuntsWithPokemon()

        bitmap = BitmapFactory.decodeResource(application.resources, R.drawable.shiny_squirtle_api)
        setup()
    }
    private val TAG = "asdfasdf"

    private val _testDex = MutableLiveData<List<PokemonShinyHunt>>().apply {
        value = null
    }
    val testDex: LiveData<List<PokemonShinyHunt>> = _testDex

    private fun setup(){
        Log.d(TAG, "setup: ")
        val dex = MainActivity.dex
        if(dex.size() > 0 ){
            Log.d(TAG, "setup: return")
            return
        }


        val tangela = Pokemon(114,"Tangela")
        val hunt1 = ShinyHunt(0, tangela, tangela.id,162,true)
        val squirtle = Pokemon(7,"Squirtle", bitmap)
        val hunt2 = ShinyHunt(0, squirtle, squirtle.id, 312,false)

        //launch a thread that will last the for as long as the viewModel does
        //IO is a thread for input / ouput








            /*while(c.moveToNext()){
                Log.d(TAG, "setup: c0 ${c.getInt(0)} c1 ${c.getString(1)} c2 ${c.getInt(2)}  c3 ${c.getInt(3)}" )
            }
        }*/


    }

}
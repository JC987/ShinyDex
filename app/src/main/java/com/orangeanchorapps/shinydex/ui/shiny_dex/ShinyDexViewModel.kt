package com.orangeanchorapps.shinydex.ui.shiny_dex


import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.*
import com.orangeanchorapps.shinydex.MainActivity
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.classes.Pokemon
import com.orangeanchorapps.shinydex.classes.ShinyHunt
import com.orangeanchorapps.shinydex.dao.PokemonDao
import com.orangeanchorapps.shinydex.database.PokemonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class ShinyDexViewModel(application: Application) : AndroidViewModel(application) {
    val allPokemon:LiveData<List<Pokemon>>
    private val pokemonDao: PokemonDao
    private val bitmap:Bitmap

    init{
        pokemonDao = PokemonDatabase.getDatabase(application).PokemonDao()

        allPokemon = pokemonDao.getAllPokemon()
        bitmap = BitmapFactory.decodeResource(application.resources, R.drawable.shiny_squirtle_api)
        setup()
    }
    private val TAG = "ShinyDex"

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private fun setup(){
        val dex = MainActivity.dex
        if(dex.size() > 0){
            return
        }
        val tangela = Pokemon(114,"Tangela")

        val squirtle = Pokemon(7,"Squirtle", bitmap)

        //launch a thread that will last the for as long as the viewModel does
        //IO is a thread for input / ouput
        viewModelScope.launch (Dispatchers.IO){
            pokemonDao.addPokemon(tangela)
            pokemonDao.addPokemon(squirtle)
        }

        dex.addHunt(
            ShinyHunt(
                0, tangela, tangela.id,162,true)
        )


        dex.addHunt(ShinyHunt(
            0, squirtle, squirtle.id, 312,false))
    }

}
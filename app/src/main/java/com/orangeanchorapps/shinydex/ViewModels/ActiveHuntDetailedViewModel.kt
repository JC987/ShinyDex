package com.orangeanchorapps.shinydex.ViewModels

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.*
import com.orangeanchorapps.shinydex.Classes.ShinyHunt
import com.orangeanchorapps.shinydex.Database.PokemonDatabase
import com.orangeanchorapps.shinydex.Database.PokemonRepository
import com.orangeanchorapps.shinydex.Database.ShinyHuntRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class ActiveHuntDetailedViewModel(application: Application) : AndroidViewModel(application) {
    private val _encounters = MutableLiveData<Int>()
    var encounters = _encounters

    private val _bitmap = MutableLiveData<Bitmap>()
    var bitmap = _bitmap

    var shinyHunt = MutableLiveData<ShinyHunt>(ShinyHunt())
    private val shinyHuntRepository : ShinyHuntRepository
    private val pokemonRepository: PokemonRepository
    init {
        val shinyHuntDAO = PokemonDatabase.getDatabase(application.applicationContext)!!.shinyHuntDAO()
        val pokemonDAO = PokemonDatabase.getDatabase(application.applicationContext)!!.pokemonDAO()
        shinyHuntRepository = ShinyHuntRepository(shinyHuntDAO)
        pokemonRepository = PokemonRepository(pokemonDAO)
    }

    fun setShinyHunt(sh : ShinyHunt){
        shinyHunt.value = sh
        encounters.value = shinyHunt.value!!.encounters

        val client = OkHttpClient()
        val request = Request.Builder().run {
            url("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${shinyHunt.value!!.pokemonId}.png")
            build()
        }

        viewModelScope.launch(Dispatchers.IO) {
            
            bitmap.postValue(pokemonRepository.fetchPokemonSprite(client, request))

        }
    }

    fun addOne(){
        shinyHunt.value!!.encounters += 1
        viewModelScope.launch(Dispatchers.IO) {
            shinyHuntRepository.updateShinyHunt(shinyHunt.value!!)
        }
        encounters.value = shinyHunt.value!!.encounters
    }
    fun subOne(){
        if(encounters.value != 0){
            shinyHunt.value!!.encounters -= 1
            viewModelScope.launch(Dispatchers.IO) {
                shinyHuntRepository.updateShinyHunt(shinyHunt.value!!)
            }
            encounters.value = shinyHunt.value!!.encounters
        }
    }

    fun finishShinyHuny() {
        shinyHunt.value!!.isActive = false
        viewModelScope.launch(Dispatchers.IO) {
            shinyHuntRepository.updateShinyHunt(shinyHunt.value!!)
        }
    }
}
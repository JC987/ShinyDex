package com.orangeanchorapps.shinydex.ViewModels

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.orangeanchorapps.shinydex.Classes.Pokemon
import com.orangeanchorapps.shinydex.Classes.ShinyHunt
import com.orangeanchorapps.shinydex.DAO.PokemonDAO
import com.orangeanchorapps.shinydex.DAO.ShinyHuntDAO
import com.orangeanchorapps.shinydex.Database.PokemonDatabase
import com.orangeanchorapps.shinydex.Database.PokemonRepository
import com.orangeanchorapps.shinydex.Database.ShinyHuntRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.Random
class LocatedPokemonViewModel(application: Application) : AndroidViewModel(application) {
    private val pokemonRepo: PokemonRepository
    private val pokemonDAO: PokemonDAO
    private val shinyHuntDAO: ShinyHuntDAO
    private val shinyHuntRepo: ShinyHuntRepository
    private val ranNum:Int

    var errorLoading = MutableLiveData<Boolean>(false)
    private val _bitmap = MutableLiveData<Bitmap>()
    var bitmap = _bitmap

    private val _pokemonName = MutableLiveData<String>()
    var pokemonName = _pokemonName

    init {

        pokemonDAO = PokemonDatabase.getDatabase(application.applicationContext)!!.pokemonDAO()
        shinyHuntDAO = PokemonDatabase.getDatabase(application.applicationContext)!!.shinyHuntDAO()

        pokemonRepo = PokemonRepository(pokemonDAO)
        shinyHuntRepo = ShinyHuntRepository(shinyHuntDAO)

        val r = Random()
        ranNum = r.nextInt(896)
        loadPokemon()
    }


    /*private fun loadName(ranNum: Int) {
        val client = OkHttpClient()
        val request = Request.Builder().run{
            url("https://pokeapi.co/api/v2/pokemon/$ranNum")
            build()
        }

        viewModelScope.launch(Dispatchers.IO) {
            val response = client.newCall(request).execute()
            val jsonObject = JSONObject(response.body!!.string())
            val name = jsonObject.getString("name")
            Log.d("loadPokemon", "loadName: $name")
            pokemonName.postValue(name)
        }
    }*/
    private fun loadName() {
        val client = OkHttpClient()
        val request = Request.Builder().run {
            url("https://pokeapi.co/api/v2/pokemon/$ranNum")
            build()
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                pokemonName.postValue(pokemonRepo.fetchPokemonName(client,request))
            } catch (e: Exception) {
                errorLoading.postValue(true)
            }
        }
    }

    fun loadPokemon(){
        loadName()
        loadImage()
    }

    private fun loadImage() {


        val client = OkHttpClient()
        val request = Request.Builder().run {
            url("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$ranNum.png")
            build()
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                bitmap.postValue(pokemonRepo.fetchPokemonSprite(client, request))
                Log.d("loadpokemon", "load: $ranNum")
            } catch(e: Exception) {
                errorLoading.postValue(true)
            }
        }

    }

    fun addToDatabase(): Job {
        val p = Pokemon(pokemonId = ranNum, pokemonName = pokemonName.value, pokemonSprite = bitmap.value )

        val sh = ShinyHunt(id= 0, pokemonId = ranNum, isActive = true, encounters = 0)

        val job = viewModelScope.launch (Dispatchers.IO) {
            pokemonRepo.addPokemon(p)
            shinyHuntRepo.addShinyHunt(sh)

        }
        return job
    }
}
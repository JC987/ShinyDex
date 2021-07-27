package com.orangeanchorapps.shinydex.ViewModels

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.core.text.isDigitsOnly
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
    private var pokemonId = MutableLiveData<Int>(1)

    var errorLoading = MutableLiveData<Boolean>(false)
    private val _bitmap = MutableLiveData<Bitmap>()
    var bitmap = _bitmap

    private val _pokemonName = MutableLiveData<String>()
    var pokemonName = _pokemonName
    var pokemonPair = MutableLiveData<Pair<String,Int>>()

    init {

        pokemonDAO = PokemonDatabase.getDatabase(application.applicationContext)!!.pokemonDAO()
        shinyHuntDAO = PokemonDatabase.getDatabase(application.applicationContext)!!.shinyHuntDAO()

        pokemonRepo = PokemonRepository(pokemonDAO)
        shinyHuntRepo = ShinyHuntRepository(shinyHuntDAO)

    }


    /*private fun loadName(pokemonId: Int) {
        val client = OkHttpClient()
        val request = Request.Builder().run{
            url("https://pokeapi.co/api/v2/pokemon/$pokemonId")
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

    fun loadRandom(){
        val r = Random()
        pokemonId.value = r.nextInt(896)
        loadPokemon()
    }
    private fun loadName() {
        val client = OkHttpClient()
        val request = Request.Builder().run {
            url("https://pokeapi.co/api/v2/pokemon/${pokemonId.value}")
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
            url("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${pokemonId.value}.png")
            build()
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                bitmap.postValue(pokemonRepo.fetchPokemonSprite(client, request))
                Log.d("loadpokemon", "load: ${pokemonId.value}")
            } catch(e: Exception) {
                errorLoading.postValue(true)
            }
        }

    }

    private fun loadPokemon(input: String){
        loadName(input)
    }

    private fun loadName(input: String) {
        val client = OkHttpClient()
        val request = Request.Builder().run {
            url("https://pokeapi.co/api/v2/pokemon/$input")
            build()
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                pokemonPair.postValue(pokemonRepo.fetchPokemonNameAndId(client,request))
            } catch (e: Exception) {
                Log.i("TAG", "loadName: $e")
                errorLoading.postValue(true)
            }
        }
    }

    fun addToDatabase(): Job {
        val p = Pokemon(pokemonId = pokemonId.value!!, pokemonName = pokemonName.value, pokemonSprite = bitmap.value )

        val sh = ShinyHunt(id= 0, pokemonId = pokemonId.value!!, isActive = true, encounters = 0)

        val job = viewModelScope.launch (Dispatchers.IO) {
            pokemonRepo.addPokemon(p)
            shinyHuntRepo.addShinyHunt(sh)

        }
        return job
    }

    fun loadInput(input: String) {
        if (input.isDigitsOnly()) {
            pokemonId.value = input.toInt()
            loadPokemon()
        } else {
            loadPokemon(input.toLowerCase())
        }
    }

    fun usePair(it: Pair<String, Int>) {
        pokemonName.value = it.first
        pokemonId.value = it.second!!
        loadImage()
    }
}
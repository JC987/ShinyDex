package com.orangeanchorapps.shinydex.ui.hunt_details

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.*
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.classes.ShinyHunt
import com.orangeanchorapps.shinydex.dao.ShinyHuntDao
import com.orangeanchorapps.shinydex.database.PokemonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class ActiveHuntDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val shinyHuntDao = PokemonDatabase.getDatabase(application).ShinyHuntDao()
    private val en:String = "Encounters: "
    private val _encounters = MutableLiveData<Int>().apply {
        value = 0
    }
    private val _textEncounters = MutableLiveData<String>().apply {
        value = "$en ${_encounters.value}"
    }
    val text: LiveData<String> = _textEncounters
    val encounters: LiveData<Int> = _encounters

    private val _spriteBitMap = MutableLiveData<Bitmap>().apply {
        value = null
    }
    val spriteBitMap: LiveData<Bitmap> = _spriteBitMap

    private val _pokemonId = MutableLiveData<Int>().apply {
        value = 7
    }
    var pokemonId: LiveData<Int> = _pokemonId

    fun setPokemonId(id: Int){
        _pokemonId.value = id
    }

    fun incrementEncounters(){
        _encounters.value = _encounters.value?.inc()
        Log.d("ShinyDex", "increment: " + _encounters.value)
        _textEncounters.value = "$en ${_encounters.value}"
    }

    fun decrementEncounters(): Boolean{
        if(_encounters.value!! > 0) {
            _encounters.value = _encounters.value?.dec()
            Log.d("ShinyDex", "decrement: " + _encounters.value)
            _textEncounters.value = "$en ${_encounters.value}"
            return true
        }
        return false
    }

    fun setEncounters(i:Int): Boolean{
        if(i > -1) {
            _encounters.value = i
            Log.d("ShinyDex", "setEncounters: new number is " + _encounters.value)
            _textEncounters.value = "$en ${_encounters.value}"
            return true
        }
        return false
    }
    fun updateShinyHunt(hunt:ShinyHunt){
        viewModelScope.launch(Dispatchers.IO) {
            shinyHuntDao.updateShinyHunt(hunt)
        }
    }

    fun loadPokemonSprite(){
            var client = OkHttpClient()
            //I will store image url to db and fetch kinda like this
            val request = Request.Builder()
                    .url("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${pokemonId.value}.png")
                    .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                   // _pokemonName.postValue(c.getString(R.string.failed_to_load_sprite))

                }

                override fun onResponse(call: Call, response: Response) {
                    var istream = response.body?.byteStream()
                    var bitmap = BitmapFactory.decodeStream(istream)

                    _spriteBitMap.postValue(bitmap)
                }

            })


    }
}
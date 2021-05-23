package com.orangeanchorapps.shinydex.Fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.Random
class LocatedPokemonViewModel : ViewModel() {
    private val _bitmap = MutableLiveData<Bitmap>()
    var bitmap = _bitmap

    private val _pokemonName = MutableLiveData<String>()
    var pokemonName = _pokemonName

    init {
        val r = Random()
        val ranNum = r.nextInt(896)
        loadImage(ranNum)
        loadName(ranNum)
    }


    private fun loadName(ranNum: Int) {
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
    }


    private fun loadImage(ranNum: Int) {


        val client = OkHttpClient()
        val requestBuilder = Request.Builder().run {
            url("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$ranNum.png")
            build()
        }

        viewModelScope.launch(Dispatchers.IO) {
            val response = client.newCall(requestBuilder).execute()
            val body = response.body
            val byteStream = body?.byteStream()
            bitmap.postValue(BitmapFactory.decodeStream(byteStream))
            Log.d("loadpokemon", "load: $ranNum" )
        }

    }
}
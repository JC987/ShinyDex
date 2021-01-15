package com.orangeanchorapps.shinydex.ui.shiny_details


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ShinyPokemonDetailViewModel : ViewModel() {

    private val TAG = "ShinyDex"

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val _pokemonName = MutableLiveData<String>().apply {
        value = "loading..."
    }

    val pokemonName: LiveData<String> = _pokemonName

    private val _spriteBitMap = MutableLiveData<Bitmap>().apply {
        value = null
    }

    val spriteBitMap: LiveData<Bitmap> = _spriteBitMap

    private val _pokemonId = MutableLiveData<Int>().apply {
        value = 114
    }
    val pokemonId: LiveData<Int> = _pokemonId


/*
    fun getTang(){
        val client = OkHttpClient()
        // completed / already caught pokemon names will be saved to db, this is just a test
        val request: Request = Request.Builder()
            .url("https://pokeapi.co/api/v2/pokemon/114")
            .build()


        client .newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle this
                Log.d(TAG, "onFailure: failed response")
            }

            override fun onResponse(call: Call, response: Response) {
                // Handle this
                //works
                var jsonObject = JSONObject(response.body?.string())

                Log.d(TAG, "onResponse: " + " :: " + jsonObject.get("abilities"))
                var name:String = jsonObject.get("name").toString()
                name = name.substring(0,1).toUpperCase() + name.substring(1)
                var j = JSONObject(jsonObject.getString("sprites"))
                var b:String = j.get("front_shiny").toString()
                Log.d(TAG, "onResponse: " + " :: " + name)



                Log.d(TAG, "onResponse: " + " :: " + b)

                //use postvalue, it is async
                _shinyName.postValue(name)

            }
        })

    }
*/


    fun setPokemonId(id:Int){
        _pokemonId.value = id
    }
    fun resetSprite(){
        _spriteBitMap.value = null
    }

    fun loadPokemonSprite(){
        Log.d(TAG, "loadPokemonSprite: ${pokemonId.value} `` ${spriteBitMap.value}")
        var client = OkHttpClient()
        //I will store image url to db and fetch kinda like this
        val request = Request.Builder()
            .url("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${pokemonId.value}.png")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "onFailure: failed to get image")
            }

            override fun onResponse(call: Call, response: Response) {
                var istream = response.body?.byteStream()
                var bitmap = BitmapFactory.decodeStream(istream)

                _spriteBitMap.postValue(bitmap)
            }

        })

    }
}

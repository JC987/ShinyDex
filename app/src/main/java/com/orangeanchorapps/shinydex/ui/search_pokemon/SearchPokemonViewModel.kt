package com.orangeanchorapps.shinydex.ui.search_pokemon


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orangeanchorapps.shinydex.R
import com.orangeanchorapps.shinydex.classes.Pokemon
import com.orangeanchorapps.shinydex.classes.ShinyDex
import com.orangeanchorapps.shinydex.classes.ShinyHunt
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.random.Random


class SearchPokemonViewModel:ViewModel() {

    private val _method = MutableLiveData<Int>().apply {
        value = 0
    }
    val text: LiveData<Int> = _method

    private val _pokemonName = MutableLiveData<String>().apply {
        value = "loading..."
    }
    val pokemonName: LiveData<String> = _pokemonName

    private val _spriteBitMap = MutableLiveData<Bitmap>().apply {
        value = null
    }

    val spriteBitMap: LiveData<Bitmap> = _spriteBitMap

    private val _success = MutableLiveData<Boolean>().apply {
        value = false
    }
    val success: LiveData<Boolean> = _success

    private val _randomId = MutableLiveData<Int>().apply {
        value = -1
    }
    val randomId: LiveData<Int> = _randomId

    fun fetchRandomPokemon(c:Context){

        val id = if(_randomId.value == -1) Random.nextInt(1, 898) else _randomId.value

        val client = OkHttpClient()

        val request:Request = Request.Builder()
            .url("https://pokeapi.co/api/v2/pokemon/$id")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("search", "onFailure: for random id")
                _pokemonName.postValue(c.getString(R.string.failed_to_load_pokemon))
            }

            override fun onResponse(call: Call, response: Response) {
                val b = response.body?.string()
                val json: JSONObject
                if (b != null) {
                    json = JSONObject(b)
                    val name = json.get("name").toString()
                    val sprite =
                        JSONObject(json.get("sprites").toString()).get("front_shiny").toString()
                    Log.d("search", "onResponse: $name : $sprite")
                    _pokemonName.postValue((name.substring(0,1).toUpperCase()) + name.substring(1))
                    getImage(c, id!!)

                }
            }

        })

    }

    fun getImage(c:Context, id: Int){
        var client = OkHttpClient()
        //I will store image url to db and fetch kinda like this
        val request = Request.Builder()
            .url("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                _pokemonName.postValue(c.getString(R.string.failed_to_load_sprite))

            }

            override fun onResponse(call: Call, response: Response) {
                var istream = response.body?.byteStream()
                var bitmap = BitmapFactory.decodeStream(istream)

                _spriteBitMap.postValue(bitmap)
            }

        })

    }

    fun addPokemon(dex: ShinyDex): Boolean{
        val n = pokemonName.value
        val b = spriteBitMap.value
        if(n != null && b != null) {
            val p = Pokemon(n, b)
            val s = ShinyHunt(p,0,false)
            dex.addHunt(s)
            return true
        }
        else
            return false
    }
}
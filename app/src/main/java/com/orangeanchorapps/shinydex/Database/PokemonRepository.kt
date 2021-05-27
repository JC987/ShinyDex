package com.orangeanchorapps.shinydex.Database

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.orangeanchorapps.shinydex.Classes.Pokemon
import com.orangeanchorapps.shinydex.DAO.PokemonDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.InputStream

//abstracts the access to multiple data sources
class PokemonRepository(val pokemonDAO: PokemonDAO) {

    suspend fun addPokemon(pokemon: Pokemon){
        pokemonDAO.addPokemon(pokemon)
    }

    //TODO add network calls here

    suspend fun fetchPokemonName(client:OkHttpClient, request: Request):String {

            val response = client.newCall(request).execute()
            val jsonObject = JSONObject(response.body!!.string())
            val name = jsonObject.getString("name")
            Log.d("loadPokemon", "loadName: $name")
            return name

    }

    suspend fun fetchPokemonSprite(client: OkHttpClient, request: Request): Bitmap {
        val response = client.newCall(request).execute()
        val body = response.body
        val byteStream = body?.byteStream()

        return BitmapFactory.decodeStream(byteStream)
    }
}
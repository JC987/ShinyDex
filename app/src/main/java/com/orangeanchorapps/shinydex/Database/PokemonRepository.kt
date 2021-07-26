package com.orangeanchorapps.shinydex.Database

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewModelScope
import com.orangeanchorapps.shinydex.Classes.Pokemon
import com.orangeanchorapps.shinydex.DAO.PokemonDAO
import com.orangeanchorapps.shinydex.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.lang.Exception

//abstracts the access to multiple data sources
class PokemonRepository(val pokemonDAO: PokemonDAO) {

    suspend fun addPokemon(pokemon: Pokemon){
        pokemonDAO.addPokemon(pokemon)
    }

    //TODO add network calls here

    suspend fun fetchPokemonName(client:OkHttpClient, request: Request):String {

            val response = client.newCall(request).execute()
            Log.i("loadPokemon", "fetchPokemonName: ")
            val jsonObject = JSONObject(response.body!!.string())
            val name = jsonObject.getString("name")
            Log.d("loadPokemon", "loadName: $name")
            return name

    }

    suspend fun fetchPokemonSprite(client: OkHttpClient, request: Request): Bitmap? {
        Log.i("TAG", "fetchPokemonSprite: ")
        var byteStream : InputStream? = null

        val response = client.newCall(request).execute()
        val body = response.body
        byteStream = body?.byteStream()


    return BitmapFactory.decodeStream(byteStream)

    }
}
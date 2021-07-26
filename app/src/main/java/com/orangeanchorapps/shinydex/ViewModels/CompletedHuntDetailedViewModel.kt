package com.orangeanchorapps.shinydex.ViewModels

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.orangeanchorapps.shinydex.Database.PokemonDatabase
import com.orangeanchorapps.shinydex.Database.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception

class CompletedHuntDetailedViewModel(application: Application) : AndroidViewModel(application) {
    val pokemonRepository : PokemonRepository
    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap = _bitmap

    var errorLoadingSprite = MutableLiveData<Boolean>(false)
    init {
        val pokemonDAO = PokemonDatabase.getDatabase(application.applicationContext)!!.pokemonDAO()

        pokemonRepository = PokemonRepository(pokemonDAO)


    }

    fun loadImage(pokemonId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder().run {
                url("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$pokemonId.png")
                build()
            }

            try {
                bitmap.postValue(pokemonRepository.fetchPokemonSprite(client, request))
            } catch (e: Exception) {
                errorLoadingSprite.postValue(true)
            }
        }
    }
}
package com.orangeanchorapps.shinydex.ui.active_hunts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orangeanchorapps.shinydex.classes.PokemonShinyHunt
import com.orangeanchorapps.shinydex.database.PokemonDatabase

class ActiveHuntsViewModel(application: Application) : AndroidViewModel(application) {
    var shinyHuntDao = PokemonDatabase.getDatabase(application).ShinyHuntDao()
    var completedHunts: LiveData<List<PokemonShinyHunt>> = shinyHuntDao.getActiveHuntsWithPokemon()


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}
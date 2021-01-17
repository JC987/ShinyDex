package com.orangeanchorapps.shinydex.ui.active_hunts

import android.app.Application
import androidx.lifecycle.*
import com.orangeanchorapps.shinydex.classes.PokemonShinyHunt
import com.orangeanchorapps.shinydex.classes.ShinyHunt
import com.orangeanchorapps.shinydex.database.PokemonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActiveHuntsViewModel(application: Application) : AndroidViewModel(application) {
    var shinyHuntDao = PokemonDatabase.getDatabase(application).ShinyHuntDao()
    val pokemonDao = PokemonDatabase.getDatabase(application).PokemonDao()
    var completedHunts: LiveData<List<PokemonShinyHunt>> = shinyHuntDao.getActiveHuntsWithPokemon()


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun deleteShinyHunt(hunt: ShinyHunt){
        viewModelScope.launch (Dispatchers.IO){
            shinyHuntDao.deleteShinyHunt(hunt)
            pokemonDao.deleteAllUnusedPokemon()
        }
    }
}
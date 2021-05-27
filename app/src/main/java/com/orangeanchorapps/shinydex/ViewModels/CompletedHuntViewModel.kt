package com.orangeanchorapps.shinydex.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.orangeanchorapps.shinydex.Classes.Pokemon
import com.orangeanchorapps.shinydex.Classes.PokemonShinyHunt
import com.orangeanchorapps.shinydex.Classes.ShinyHunt
import com.orangeanchorapps.shinydex.Database.PokemonDatabase
import com.orangeanchorapps.shinydex.Database.ShinyHuntRepository

class CompletedHuntViewModel(application: Application) : AndroidViewModel(application) {

    val getAllCompleted : LiveData<List<PokemonShinyHunt>>
    val shinyHuntRepository: ShinyHuntRepository
    init {
        val shinyHuntDAO = PokemonDatabase.getDatabase(application.applicationContext)!!.shinyHuntDAO()
        shinyHuntRepository = ShinyHuntRepository(shinyHuntDAO)
        getAllCompleted = shinyHuntDAO.getCompletedHuntsWithPokemon()

    }

}
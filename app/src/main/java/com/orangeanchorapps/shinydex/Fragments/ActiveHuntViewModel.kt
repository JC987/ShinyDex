package com.orangeanchorapps.shinydex.Fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.orangeanchorapps.shinydex.Classes.PokemonShinyHunt
import com.orangeanchorapps.shinydex.Classes.ShinyHunt
import com.orangeanchorapps.shinydex.Database.PokemonDatabase
import com.orangeanchorapps.shinydex.Database.ShinyHuntRepository
import java.lang.Appendable

class ActiveHuntViewModel(application: Application) : AndroidViewModel(application) {
    private val shinyHuntRepo : ShinyHuntRepository
    val activeShinyHunts: LiveData<List<PokemonShinyHunt>>
    init {
        val shinyHuntDAO = PokemonDatabase.getDatabase(application.applicationContext)!!.shinyHuntDAO()
        shinyHuntRepo = ShinyHuntRepository(shinyHuntDAO)
        activeShinyHunts = shinyHuntRepo.activeShinyHunts
    }


}
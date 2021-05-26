package com.orangeanchorapps.shinydex.Database

import com.orangeanchorapps.shinydex.Classes.ShinyHunt
import com.orangeanchorapps.shinydex.DAO.ShinyHuntDAO

class ShinyHuntRepository(val shinyHuntDAO: ShinyHuntDAO) {

    val activeShinyHunts = shinyHuntDAO.getActiveHuntsWithPokemon()
    val completedShinyHunts = shinyHuntDAO.getAllCompletedShinyHunts()

    suspend fun addShinyHunt(shinyHunt: ShinyHunt){
        shinyHuntDAO.addNewShinyHunt(shinyHunt)
    }
    suspend fun updateShinyHunt(shinyHunt: ShinyHunt){
        shinyHuntDAO.updateShinyHunt(shinyHunt)
    }
    suspend fun deleteShinyHunt(shinyHunt: ShinyHunt){
        shinyHuntDAO.deleteShinyHunt(shinyHunt)
    }
}
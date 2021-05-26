package com.orangeanchorapps.shinydex.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.orangeanchorapps.shinydex.Classes.ShinyHunt

@Dao
interface ShinyHuntDAO {

    @Insert()
    suspend fun addNewShinyHunt(shinyHunt: ShinyHunt)

    @Update()
    suspend fun updateShinyHunt(shinyHunt: ShinyHunt)

    @Delete
    suspend fun deleteShinyHunt(shinyHunt: ShinyHunt)

    @Query("SELECT * FROM shiny_hunt WHERE isActive == 1")
    fun getAllActiveShinyHunts():LiveData<List<ShinyHunt>>

    @Query("SELECT * FROM shiny_hunt WHERE isActive == 0")
    fun getAllCompletedShinyHunts():LiveData<List<ShinyHunt>>
}












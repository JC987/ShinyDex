package com.orangeanchorapps.shinydex.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orangeanchorapps.shinydex.classes.ShinyHunt

@Dao
interface ShinyHuntDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShinyHunt(shinyHunt: ShinyHunt)

    @Query("SELECT * FROM shiny_hunt")
    fun getAllShinyHunts(): LiveData<List<ShinyHunt>>

    @Query("DELETE from shiny_hunt")
    fun deleteAllShinyHunt()
}
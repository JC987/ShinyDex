package com.orangeanchorapps.shinydex.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.orangeanchorapps.shinydex.classes.Pokemon
import com.orangeanchorapps.shinydex.classes.PokemonShinyHunt
import com.orangeanchorapps.shinydex.classes.ShinyHunt

@Dao
interface ShinyHuntDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShinyHunt(shinyHunt: ShinyHunt)

    @Update()
    suspend fun updateShinyHunt(shinyHunt: ShinyHunt)

    @Delete
    suspend fun deleteShinyHunt(shinyHunt: ShinyHunt)

    @Query("SELECT * FROM shiny_hunt")
    fun getAllShinyHunts(): LiveData<List<ShinyHunt>>

    @Query("DELETE from shiny_hunt")
    fun deleteAllShinyHunt()

    //@Query("SELECT s.id as id, p.id as pokemonId, p.name as pokemonName, s.encounters, s.isCompleted FROM shiny_hunt AS s INNER JOIN pokemon AS p ON s.pokemonId = p.id WHERE p.id == 114")
    @Query("SELECT s.id as id, p.id as pokemonId, p.name as pokemonName, s.encounters, s.isCompleted FROM shiny_hunt AS s INNER JOIN pokemon AS p ON s.pokemonId = p.id")
    fun getAllHuntsWithPokemon(): LiveData<List<PokemonShinyHunt>>

    @Query("SELECT s.id as id, p.id as pokemonId, p.name as pokemonName, s.encounters, s.isCompleted FROM shiny_hunt AS s INNER JOIN pokemon AS p ON s.pokemonId = p.id WHERE s.isCompleted == 0")
    fun getActiveHuntsWithPokemon(): LiveData<List<PokemonShinyHunt>>

    @Query("SELECT s.id as id, p.id as pokemonId, p.name as pokemonName, s.encounters, s.isCompleted FROM shiny_hunt AS s INNER JOIN pokemon AS p ON s.pokemonId = p.id WHERE s.isCompleted == 1")
    fun getCompletedHuntsWithPokemon(): LiveData<List<PokemonShinyHunt>>
}
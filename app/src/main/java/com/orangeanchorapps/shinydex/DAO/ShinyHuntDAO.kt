package com.orangeanchorapps.shinydex.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.orangeanchorapps.shinydex.Classes.PokemonShinyHunt
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

    @Query("SELECT s.id as id, p.pokemonId as pokemonId, p.pokemonName as pokemonName, s.encounters, s.isActive FROM shiny_hunt AS s INNER JOIN pokemon_table AS p ON s.pokemonId = p.pokemonId WHERE s.isActive == 1")
    fun getActiveHuntsWithPokemon(): LiveData<List<PokemonShinyHunt>>

    @Query("SELECT s.id as id, p.pokemonId as pokemonId, p.pokemonName as pokemonName, s.encounters, s.isActive FROM shiny_hunt as S INNER JOIN pokemon_table as p on s.pokemonId = p.pokemonId WHERE s.isActive == 0")
    fun getCompletedHuntsWithPokemon(): LiveData<List<PokemonShinyHunt>>
}












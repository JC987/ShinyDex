package com.orangeanchorapps.shinydex.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orangeanchorapps.shinydex.classes.Pokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPokemon(pokemon: Pokemon)

    @Query("DELETE FROM pokemon WHERE id IN (SELECT  p.id FROM pokemon AS p left join shiny_hunt as s on s.pokemonId == p.id where s.id is null ) ")
    suspend fun deleteAllUnusedPokemon()

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon():LiveData<List<Pokemon>>

    @Query("DELETE FROM pokemon")
    suspend fun deleteAllPokemon()
}
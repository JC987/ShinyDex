package com.orangeanchorapps.shinydex.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.orangeanchorapps.shinydex.Classes.Pokemon

@Dao
interface PokemonDAO {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPokemon(pokemon: Pokemon)

    @Query("DELETE FROM pokemon_table")
    suspend fun nukeTable()



    // I dont think it is needed
    //suspend fun getAllPokemon():LiveData<List<Pokemon>>
}
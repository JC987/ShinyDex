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

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon():LiveData<List<Pokemon>>
}
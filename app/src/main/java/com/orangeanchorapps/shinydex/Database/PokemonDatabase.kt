package com.orangeanchorapps.shinydex.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.orangeanchorapps.shinydex.Classes.Pokemon
import com.orangeanchorapps.shinydex.Classes.ShinyHunt
import com.orangeanchorapps.shinydex.DAO.PokemonDAO
import com.orangeanchorapps.shinydex.DAO.ShinyHuntDAO

@Database(entities = [Pokemon::class, ShinyHunt::class], version = 2)

abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDAO(): PokemonDAO
    abstract fun shinyHuntDAO(): ShinyHuntDAO

    companion object{
        //write are immediatly seen on all threads.
        @Volatile
        var INSTANCE: PokemonDatabase? = null



        fun getDatabase(appContext: Context): PokemonDatabase? {
            if(INSTANCE != null)
                return INSTANCE

            synchronized(this){
                val tmp = Room.databaseBuilder(
                    appContext,
                    PokemonDatabase::class.java,
                    "pokemon_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = tmp
                return INSTANCE
            }
        }
    }
}
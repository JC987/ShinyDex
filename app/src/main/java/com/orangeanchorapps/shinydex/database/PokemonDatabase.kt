package com.orangeanchorapps.shinydex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.orangeanchorapps.shinydex.classes.Pokemon
import com.orangeanchorapps.shinydex.classes.ShinyHunt
import com.orangeanchorapps.shinydex.dao.PokemonDao
import com.orangeanchorapps.shinydex.dao.ShinyHuntDao

//export schema is for keeping history of data in code base
@Database(entities = [Pokemon::class, ShinyHunt::class], version = 2, exportSchema = false)
abstract class PokemonDatabase: RoomDatabase() {
    //Room will provide implementation for this
    abstract fun PokemonDao(): PokemonDao
    abstract fun ShinyHuntDao(): ShinyHuntDao
    companion object{
        //updates will immediately show on all threads
        @Volatile
        private var dbInstance:PokemonDatabase? = null

        fun getDatabase(context:Context): PokemonDatabase{
            if(dbInstance != null){
                return dbInstance!!
            }

            //prevents multiple threads from executing this code block
            synchronized(this){
                dbInstance = Room.databaseBuilder(
                    context,PokemonDatabase::class.java,"pokemon_database"
                ).fallbackToDestructiveMigration().build()

                return dbInstance!!
            }

        }
    }
}
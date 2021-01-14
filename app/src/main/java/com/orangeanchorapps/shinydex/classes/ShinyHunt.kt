package com.orangeanchorapps.shinydex.classes

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "shiny_hunt")
data class ShinyHunt(@PrimaryKey(autoGenerate = true) val id:Int,
                     @Ignore var pokemon:Pokemon,
                     var pokemonId: Int = pokemon.id,
                     var encounters:Int,
                     var isCompleted:Boolean) {

    fun changeEncounters(adding:Boolean){
        if(isCompleted)
            return

        if(adding)
            encounters++
        else
            if(encounters>0)
                encounters--
    }
}
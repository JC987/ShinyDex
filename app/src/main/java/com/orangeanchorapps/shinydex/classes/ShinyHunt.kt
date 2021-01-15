package com.orangeanchorapps.shinydex.classes

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "shiny_hunt",
        foreignKeys = arrayOf(ForeignKey(
                entity = Pokemon::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("pokemonId"),
                onDelete = ForeignKey.CASCADE
        ))
)
data class ShinyHunt(@PrimaryKey(autoGenerate = true) var id:Int,
                     @Ignore var pokemon:Pokemon,
                     var pokemonId: Int = pokemon.id,
                     var encounters:Int,
                     var isCompleted:Boolean) {


    constructor():this(0, Pokemon(),0, 0,false)

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
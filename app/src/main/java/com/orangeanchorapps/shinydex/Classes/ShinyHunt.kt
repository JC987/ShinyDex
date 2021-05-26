package com.orangeanchorapps.shinydex.Classes

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "shiny_hunt",
        foreignKeys = arrayOf(ForeignKey(
        entity = Pokemon::class,
        parentColumns = arrayOf("pokemonId"),
        childColumns = arrayOf("pokemonId"),
        onDelete = ForeignKey.CASCADE
        ))
)
data class ShinyHunt(
    @PrimaryKey (autoGenerate = true)
    var id:Int = 0,

    @Ignore
    var pokemon: Pokemon? = null,

    var pokemonId: Int = 0,

    var isActive : Boolean = true,

    var encounters: Int = 0) {
}
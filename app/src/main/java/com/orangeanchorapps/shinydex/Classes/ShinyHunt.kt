package com.orangeanchorapps.shinydex.Classes

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "shiny_hunt")
data class ShinyHunt(
    @PrimaryKey (autoGenerate = true)
    var id:Int = 0,

    var pokemonId: Int = 0,

    var isActive : Boolean = true,

    var encounters: Int = 0) {
}
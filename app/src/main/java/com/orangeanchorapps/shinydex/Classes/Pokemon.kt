package com.orangeanchorapps.shinydex.Classes

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class Pokemon (
        @PrimaryKey(autoGenerate = false)
        var pokemonId: Int = 0,

        var pokemonName: String? = "loading name.....",

        @Ignore var pokemonSprite: Bitmap? = null
){

}
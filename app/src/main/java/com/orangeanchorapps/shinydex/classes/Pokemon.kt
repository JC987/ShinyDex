package com.orangeanchorapps.shinydex.classes

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(@PrimaryKey(autoGenerate = false) var id:Int = -1,
                   var name:String = "Loading...",
                   @Ignore var sprite: Bitmap? = null) {

    fun hasImage():Boolean{
        return (sprite != null)
    }

    fun isLoaded():Boolean{
        return name!= "Loading..."
    }
}
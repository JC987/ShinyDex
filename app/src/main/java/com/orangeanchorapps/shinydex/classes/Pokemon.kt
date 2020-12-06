package com.orangeanchorapps.shinydex.classes

import android.graphics.Bitmap

data class Pokemon(var name:String = "Loading...", var sprite: Bitmap? = null) {
    constructor(name:String, sprite:Bitmap, type1:String, type2:String): this(name, sprite)

    fun hasImage():Boolean{
        return (sprite != null)
    }

    fun isLoaded():Boolean{
        return name!= "Loading..."
    }
}
package com.orangeanchorapps.shinydex.Classes

data class PokemonShinyHunt (
        var id:Int,
        var pokemonId:Int,
        var pokemonName:String,
        var encounters:Int,
        var isActive:Boolean
) {
}
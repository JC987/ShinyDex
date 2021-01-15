package com.orangeanchorapps.shinydex.classes

data class PokemonShinyHunt(
        var id:Int,
        var pokemonId:Int,
        var pokemonName:String,
        var encounters:Int,
        var isCompleted:Boolean
) {
}
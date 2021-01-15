package com.orangeanchorapps.shinydex.classes

import android.util.Log
import com.orangeanchorapps.shinydex.MainActivity

class ShinyDex(val dex: ArrayList<ShinyHunt> = ArrayList<ShinyHunt>()) {

    fun addHunt(hunt: ShinyHunt){
        dex.add(hunt)
    }

    fun remove(index:Int): Boolean{
        if(index >= 0 && index < size()){
            return dex.remove(dex[index])
        }
        return false
    }

    fun size():Int{
        return dex.size
    }

    fun getHunt(index: Int): ShinyHunt{
        return dex[index]
    }

    fun getCompletedHunts():List<ShinyHunt>{
        val tmp = ArrayList<ShinyHunt>()
        for(item:ShinyHunt in dex){
            if(item.isCompleted)
                tmp.add(item)
        }
        return tmp
    }

    fun getCompletedHuntsNames():List<String>{
        val tmp = ArrayList<String>()
        for(item:ShinyHunt in dex){
            if(item.isCompleted)
                tmp.add(item.pokemon.name)
        }
        return tmp
    }

    fun getActiveHunts():ArrayList<ShinyHunt>{
        val tmp = ArrayList<ShinyHunt>()
        for(item:ShinyHunt in dex){
            if(!item.isCompleted)
                tmp.add(item)
        }
        return tmp
    }

    fun getActiveHuntsName():ArrayList<String>{
        val tmp = ArrayList<String>()
        for(item:ShinyHunt in dex){
            if(!item.isCompleted)
                tmp.add(item.pokemon.name + " ~ (Encounters: " + item.encounters + ")")
        }
        return tmp
    }

    fun reset(list: List<PokemonShinyHunt>) {
        dex.clear()
        for(item:PokemonShinyHunt in list){

           this.dex.add(
                    ShinyHunt(
                            item.id,
                            Pokemon(item.pokemonId, item.pokemonName),
                            item.pokemonId,
                            item.encounters,
                            item.isCompleted
                    ))

            Log.d("ShinyDex", "update: ${item.pokemonName}")
        }
    }
}
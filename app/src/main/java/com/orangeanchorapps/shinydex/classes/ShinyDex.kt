package com.orangeanchorapps.shinydex.classes

class ShinyDex(private val dex: ArrayList<ShinyHunt> = ArrayList<ShinyHunt>()) {

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
}
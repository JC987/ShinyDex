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

}
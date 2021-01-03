package com.orangeanchorapps.shinydex.classes

data class ShinyHunt(val pokemon:Pokemon, var encounters:Int, var isCompleted:Boolean) {

    fun changeEncounters(adding:Boolean){
        if(isCompleted)
            return

        if(adding)
            encounters++
        else
            if(encounters>0)
                encounters--
    }
}
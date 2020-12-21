package com.orangeanchorapps.shinydex.ui.hunt_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActiveHuntDetailsViewModel : ViewModel() {
    private val en:String = "Encounters: "
    private val _encounters = MutableLiveData<Int>().apply {
        value = 0
    }
    private val _textEncounters = MutableLiveData<String>().apply {
        value = "$en ${_encounters.value}"
    }
    val text: LiveData<String> = _textEncounters
    val encounters: LiveData<Int> = _encounters

    fun incrementEncounters(){
        _encounters.value = _encounters.value?.inc()
        Log.d("ShinyDex", "increment: " + _encounters.value)
        _textEncounters.value = "$en ${_encounters.value}"
    }

    fun decrementEncounters(): Boolean{
        if(_encounters.value!! > 0) {
            _encounters.value = _encounters.value?.dec()
            Log.d("ShinyDex", "decrement: " + _encounters.value)
            _textEncounters.value = "$en ${_encounters.value}"
            return true
        }
        return false
    }

    fun setEncounters(i:Int): Boolean{
        if(i > -1) {
            _encounters.value = i
            Log.d("ShinyDex", "setEncounters: new number is " + _encounters.value)
            _textEncounters.value = "$en ${_encounters.value}"
            return true
        }
        return false
    }
}
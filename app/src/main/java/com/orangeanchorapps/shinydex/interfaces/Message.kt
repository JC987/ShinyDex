package com.orangeanchorapps.shinydex.interfaces

interface Message {
    var messageInt:Int

    fun setMessage(i:Int)

    fun receiveMessage():Int
}